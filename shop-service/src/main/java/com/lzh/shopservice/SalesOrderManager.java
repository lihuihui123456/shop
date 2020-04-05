package com.lzh.shopservice;

import com.lzh.shopcommon.page.Page;
import com.lzh.shopcommon.util1.UID;
import com.lzh.shop.dao.SalesOrderDao;
import com.lzh.shop.dao.SalesOrderHeaderDao;
import com.lzh.shopentity.SalesOrder;
import com.lzh.shopentity.SalesOrderHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 订单明细管理
 */
@Service
public class SalesOrderManager {

    @Autowired
    private SalesOrderDao salesOrderDao;
    @Autowired
    private SalesOrderHeaderDao salesOrderHeaderDao;

    public SalesOrder getSalesOrder(Long salesOrderId) {
        return salesOrderDao.get(salesOrderId);
    }

    public void insert(SalesOrder salesOrder) {
        salesOrderDao.insert(salesOrder);
    }

    public Page<SalesOrder> listSalesOrder(Page<SalesOrder> page, Map<String, Object> filters) {
        return salesOrderDao.findPage(page,"", filters);
    }
    public List<SalesOrder> listSalesOrder(Map<String, Object> filters) {
        return salesOrderDao.findListForMap(filters);
    }
    public void update(SalesOrder salesOrder) {
        salesOrderDao.update(salesOrder);
    }
    /**
     * 根据购物车信息生成订单
     * @param cartOrderIds  购物车清单
     * @param recevieAddress 收货人信息
     * @param messege  备注信息
     */
    public void createSalesOrder(List<Long> cartOrderIds,Long recevieAddressId,String messege,Double coupon){
     //生成订单头  header
        SalesOrderHeader salesOrderHeader=new SalesOrderHeader();
        salesOrderHeader.setId(UID.next());
        salesOrderHeader.setVersion(10);
        salesOrderHeader.setTotalAmount(cartOrderIds.size());
        salesOrderHeader.setReceiveAddressId(recevieAddressId);
        salesOrderHeader.setNote(messege);
        salesOrderHeader.setCreateDate(new Date());
        //生成明细
        List<SalesOrder> salesOrderList=new ArrayList<>();
        for(Long cartOrderId: cartOrderIds){
            //根据cartOrderId获取购物车清单数据
            SalesOrder  salesOrder =new SalesOrder();
            salesOrder.setId(UID.next());
            salesOrder.setVersion(10);
            salesOrder.setOrderHeaderId(salesOrderHeader.getId());
            salesOrder.setGoodsId(1111l);
            salesOrder.setOrderingTime(new Date());
            salesOrderList.add(salesOrder);
        }
        //保存订单头明细和删除购物车清单
        salesOrderHeaderDao.insert(salesOrderHeader);
        salesOrderDao.batchInsert(salesOrderList);
        //删除购物车清单  TODO
    }

    /**
     * 订单取消
     * @param salesOrderHeaderId
     */
    public  void cancelSalesOrder(Long salesOrderHeaderId){
        SalesOrderHeader salesOrderHeader = salesOrderHeaderDao.get(salesOrderHeaderId);
        salesOrderHeader.setAllOrderClosed(true);
        salesOrderHeaderDao.update(salesOrderHeader);
        Map filters=new HashMap();
        filters.put("salesOrderHeaderId",salesOrderHeader.getId());
        List<SalesOrder> listForMap = salesOrderDao.findListForMap(filters);
        for(SalesOrder salesOrder :listForMap){
            salesOrder.setOrderStatus(11);
            salesOrderDao.update(salesOrder);
        }
    }
    public  void deleteSalesOrder(Long salesOrderHeaderId){
        SalesOrderHeader salesOrderHeader = salesOrderHeaderDao.get(salesOrderHeaderId);
        salesOrderHeader.setIsDelete(1);
        salesOrderHeaderDao.update(salesOrderHeader);
        Map filters=new HashMap();
        filters.put("salesOrderHeaderId",salesOrderHeader.getId());
        List<SalesOrder> listForMap = salesOrderDao.findListForMap(filters);
        for(SalesOrder salesOrder :listForMap){
            salesOrder.setIsDelete(1);
            salesOrderDao.update(salesOrder);
        }
    }
}
