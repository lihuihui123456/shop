package com.lzh.shopweb.facade;

import com.lzh.shopcommon.page.Page;
import com.lzh.shopentity.CartOrder;
import com.lzh.shopservice.CartOrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CartOrderFacade {
    @Autowired
    private CartOrderManager cartOrderManager;
    public CartOrder getCartOrder(Long cartOrderId) {
        return cartOrderManager.getCartOrder(cartOrderId);
    }
    public void update(CartOrder cartOrder) {
        cartOrderManager.update(cartOrder);
    }

    public void insert(CartOrder salesOrder) {
        cartOrderManager.insert(salesOrder);
    }
    public void delete(Long cartOrderId) {
        cartOrderManager.delete(cartOrderId);
    }

    public void batchDelete(List<Long> cartOrderIds) {
        cartOrderManager.batchDelete(cartOrderIds);
    }
    public List<CartOrder> listCartOrder(Map<String, Object> filters) {
        return cartOrderManager.listCartOrder(filters);
    }
    public Page<CartOrder> listCartOrder(Page<CartOrder> page, Map<String, Object> filters) {
        return cartOrderManager.listCartOrder(page, filters);
    }
}
