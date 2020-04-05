package com.lzh.shop.dao;

import com.lzh.shop.dao.writeDataSource.MyBatisDao;
import com.lzh.shopentity.CartOrder;
import org.springframework.stereotype.Repository;

@Repository
public class CartOrderDao extends MyBatisDao<CartOrder> {

}