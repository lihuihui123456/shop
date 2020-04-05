package com.lzh.shop.mapper;

import com.lzh.shopentity.ReceiveAddress;

import java.util.List;
import java.util.Map;

public interface ReceiveAddressMapper extends BaseMapper<ReceiveAddress>{
    List<ReceiveAddress> findListForMapByPartyId(Map<String , Object> filter);
}
