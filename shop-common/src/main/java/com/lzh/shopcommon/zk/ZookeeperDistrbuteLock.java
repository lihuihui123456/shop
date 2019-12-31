package com.lzh.shopcommon.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * zk分布式锁
 *
 * 实现原理是根据zk的临时有序节点和watcher通知机制
 */
public class ZookeeperDistrbuteLock  implements Lock {
    private static final String CONNECTION="192.168.91.5:2181";
    protected CuratorFramework zk = null;
    private String lockPath="/lockPath";
    private String lockName;//竞争资源的标志
    private String waitNode;//等待前一个锁
    private String myZnode;//当前锁
    private CountDownLatch latch;//计数器
    private CountDownLatch connectedSignal=new CountDownLatch(1);
    private int sessionTimeout = 30000;

    /**
     *      * @param lockName 竞争资源标志,lockName中不能包含单词_lock_
     */
    public ZookeeperDistrbuteLock(String lockName){
        this.lockName=lockName;
        try{
            //1 重试策略：初试时间为1s 重试10次
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
            //2 通过工厂创建连接
            zk = CuratorFrameworkFactory.builder()
                    .connectString(CONNECTION)
                    .sessionTimeoutMs(sessionTimeout)
                    .retryPolicy(retryPolicy)
                    .build();
            zk.start();
            //connectedSignal.await();
            Stat stat = zk.checkExists().forPath(lockPath);
            //Stat stat = zk.checkExists(lockPath, false);//此去不执行 Watcher
            if(stat == null){
                // 创建根节点
                zk.create().forPath(lockPath, new byte[0]);
            }
        }catch (Exception ex){
          ex.getMessage();
        }


    }
    @Override
    public void lock() {
        try {
            if(this.tryLock()){
                System.out.println("Thread " + Thread.currentThread().getId() + " " +myZnode + " get lock true");
                return;
            }
            else{
               // waitForLock(waitNode, sessionTimeout);//等待锁
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
            try {
                String splitStr = "_lock_";
                if(lockName.contains(splitStr)) throw new Exception("lockName can not contains \\u000B");
                //创建临时有序子节点
                myZnode =zk.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL) .forPath(lockPath + "/" + lockName+splitStr,new byte[0]);
                System.out.println(myZnode + " is created ");
                //循环匹配当前节点的下标是否为第一个子节点
                while (true){
                    List<String> stringsAll = zk.getChildren().forPath(lockPath);
                    //获取lockName类型的锁，因为跟路径下边可以存放多种类型锁
                    //取出所有lockName的锁
                    List<String> lockObjNodes = new ArrayList<String>();
                    for (String node : stringsAll) {
                        String _node = node.split(splitStr)[0];
                        if(_node.equals(lockName)){
                            lockObjNodes.add(node);
                        }
                    }
                    Collections.sort(lockObjNodes);
                    //判断当前节点是否为当前类型的第一个节点
                    if(myZnode.equals(lockPath+"/"+lockObjNodes.get(0))){
                        //如果是最小的节点,则表示取得锁
                        System.out.println(myZnode + "==" + lockObjNodes.get(0));
                        return true;
                    }
                    //如果不是最小的节点就进行监听上一个节点和进行线程等待
                    //如果不是最小的节点，找到比自己小1的节点
                    String subMyZnode = myZnode.substring(myZnode.lastIndexOf("/") + 1);
                    waitNode = lockObjNodes.get(Collections.binarySearch(lockObjNodes, subMyZnode) - 1);//找到前一个子节点
                    //添加监听
                    PathChildrenCache cache = new PathChildrenCache(zk, waitNode, true);
                    ZkLockListener zkLockListener=new ZkLockListener();
                    cache.getListenable().addListener(zkLockListener);
                    cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
                    this.wait();
                }
            } catch (Exception e) {
                e.getMessage();
            }
            return false;
        }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            if(this.tryLock()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean waitForLock(String lower, long waitTime) throws InterruptedException, KeeperException {
        return true;
    }

    /**
     * zookeeper节点的监视器
     */
    public void process(WatchedEvent event) {
        //建立连接用
        if(event.getState()==Watcher.Event.KeeperState.SyncConnected){
            connectedSignal.countDown();
            return;
        }
        //其他线程放弃锁的标志
        if(this.latch != null) {
            this.latch.countDown();
        }
    }
    @Override
    public void unlock() {
        //执行完毕 直接连接
        if (zk != null) {
            zk.close();
            System.out.println("######释放锁完毕######");
        }
    }
    public void closeZk() {
        //执行完毕 直接连接
        if (zk != null) {
            zk.close();
            System.out.println("######释放锁完毕######");
        }
    }
    @Override
    public Condition newCondition() {
        return null;
    }
    class ZkLockListener implements PathChildrenCacheListener{

        @Override
        public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
            try {
                ChildData data = pathChildrenCacheEvent.getData();
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:

                        break;
                    case CHILD_UPDATED:
                        break;
                    case CHILD_REMOVED:
                        this.notify();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
