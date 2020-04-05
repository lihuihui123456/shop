package com.lzh.shop.dao;

import com.lzh.shop.dao.writeDataSource.MyBatisDao;
import com.lzh.shop.mapper.ReceiveAddressMapper;
import com.lzh.shopentity.ReceiveAddress;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReceiveAddressDao extends MyBatisDao<ReceiveAddress> {
    public List<ReceiveAddress> findListForMapByPartyId(Map<String,Object> filters){
        return 	getMapper(ReceiveAddressMapper.class).findListForMapByPartyId(filters);
    }
}