package com.lzh.shop.dao;

import com.lzh.shop.dao.writeDataSource.MyBatisDao;
import com.lzh.shopentity.SalesOrder;
import org.springframework.stereotype.Repository;

@Repository
public class SalesOrderDao extends MyBatisDao<SalesOrder> {
}
