package com.lzh.shopservice;

import com.lzh.shopcommon.page.Page;
import com.lzh.shop.dao.GoodsDao;
import com.lzh.shopentity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsManager {
    @Autowired
    private GoodsDao goodsDao;

    public Goods getGoods(Long goodsId) {
        return goodsDao.get(goodsId);
    }
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    public void insert(Goods goods) {
        goodsDao.insert(goods);
    }
    public void delete(Long goodsId) {
        goodsDao.delete(goodsId);
    }

    public void batchDelete(List<Long> goodsIds) {
        goodsDao.batchDelete(goodsIds);
    }
    public List<Goods> listGoods(Map<String, Object> filters) {
        return goodsDao.findListForMap(filters);
    }
    public Page<Goods> listGoods(Page<Goods> page, Map<String, Object> filters) {
        return goodsDao.findPage(page,"findListForMap", filters);
    }
}
