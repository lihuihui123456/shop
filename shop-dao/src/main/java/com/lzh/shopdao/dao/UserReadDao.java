package com.lzh.shopdao.dao;

import com.lzh.shopdao.mapper.GgcIntelligentAllotOrderHeaderMapper;
import com.lzh.shopdao.mapper.readMapper.UserMapper;
import com.lzh.shopentity.GgcIntelligentAllotOrderHeader;
import com.lzh.shopentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserReadDao extends MyBatisDao<User>{
    @Autowired
    private UserMapper userMapper;
    public  User get(Long id){
        return  userMapper.get(id);
    }

}