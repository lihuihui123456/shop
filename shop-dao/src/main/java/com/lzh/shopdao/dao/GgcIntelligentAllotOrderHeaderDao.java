package com.lzh.shopdao.dao;

import com.lzh.shopdao.mapper.GgcIntelligentAllotOrderHeaderMapper;
import com.lzh.shopdao.mapper.readMapper.UserMapper;
import com.lzh.shopentity.GgcIntelligentAllotOrderHeader;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class GgcIntelligentAllotOrderHeaderDao extends MyBatisDao<GgcIntelligentAllotOrderHeader>{
  /*  @Resource
     public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
                 super.setSqlSessionFactory(sqlSessionFactory); }*/
    @Autowired
    private GgcIntelligentAllotOrderHeaderMapper ggcIntelligentAllotOrderHeaderMapper;
  @Autowired
  private UserMapper userMapper;
    public int findListForMapCountSize(Map filter) {
        return ggcIntelligentAllotOrderHeaderMapper.findListForMapCountSize(filter);
    }

}