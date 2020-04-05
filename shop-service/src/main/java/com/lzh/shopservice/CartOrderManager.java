package com.lzh.shopservice;

import com.lzh.shopcommon.page.Page;
import com.lzh.shop.dao.CartOrderDao;
import com.lzh.shopentity.CartOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartOrderManager {
    @Autowired
    private CartOrderDao cartOrderDao;
    public CartOrder getCartOrder(Long cartOrderId) {
        return cartOrderDao.get(cartOrderId);
    }
    public void update(CartOrder cartOrder) {
        cartOrderDao.update(cartOrder);
    }

    public void insert(CartOrder cartOrder) {
        cartOrderDao.insert(cartOrder);
    }
    public void delete(Long cartOrderId) {
        cartOrderDao.delete(cartOrderId);
    }

    public void batchDelete(List<Long> cartOrderIds) {
        cartOrderDao.batchDelete(cartOrderIds);
    }
    public List<CartOrder> listCartOrder(Map<String, Object> filters) {
        return cartOrderDao.findListForMap(filters);
    }
    public Page<CartOrder> listCartOrder(Page<CartOrder> page, Map<String, Object> filters) {
        return cartOrderDao.findPage(page,"findListForMap", filters);
    }
}
