package com.lzh.shop.dao;

import com.lzh.shop.dao.writeDataSource.MyBatisDao;
import com.lzh.shopentity.Goods;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDao extends MyBatisDao<Goods> {

}