package com.lzh.shopweb.facade;

import com.lzh.shopcommon.page.Page;
import com.lzh.shopentity.Goods;
import com.lzh.shopservice.GoodsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GoodsFacade {
    @Autowired
    private GoodsManager goodsManager;
    public Goods getGoods(Long goodsId) {
        return goodsManager.getGoods(goodsId);
    }
    public void update(Goods goods) {
        goodsManager.update(goods);
    }

    public void insert(Goods goods) {
        goodsManager.insert(goods);
    }
    public void delete(Long goodsId) {
        goodsManager.delete(goodsId);
    }

    public void batchDelete(List<Long> goodsIds) {
        goodsManager.batchDelete(goodsIds);
    }
    public List<Goods> listGoods(Map<String, Object> filters) {
        return goodsManager.listGoods(filters);
    }
    public Page<Goods> listGoods(Page<Goods> page, Map<String, Object> filters) {
        return goodsManager.listGoods(page, filters);
    }
}
