package com.lzh.shop.dao;

import com.lzh.shop.dao.writeDataSource.MyBatisDao;
import com.lzh.shop.mapper.GgcIntelligentAllotOrderHeaderMapper;
import com.lzh.shopentity.GgcIntelligentAllotOrderHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class GgcIntelligentAllotOrderHeaderDao extends MyBatisDao<GgcIntelligentAllotOrderHeader> {
  /*  @Resource
     public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
                 super.setSqlSessionFactory(sqlSessionFactory); }*/
    @Autowired
    private GgcIntelligentAllotOrderHeaderMapper ggcIntelligentAllotOrderHeaderMapper;
    public int findListForMapCountSize(Map filter) {
        return ggcIntelligentAllotOrderHeaderMapper.findListForMapCountSize(filter);
    }

}