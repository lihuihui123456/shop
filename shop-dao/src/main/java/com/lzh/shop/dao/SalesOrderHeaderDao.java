package com.lzh.shop.dao;

import com.lzh.shop.dao.writeDataSource.MyBatisDao;
import com.lzh.shopentity.SalesOrderHeader;
import org.springframework.stereotype.Repository;

@Repository
public class SalesOrderHeaderDao extends MyBatisDao<SalesOrderHeader> {
}
