package com.lzh.shop.dao;

import com.lzh.shop.dao.writeDataSource.MyBatisDao;
import com.lzh.shopentity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends MyBatisDao<User> {

}
