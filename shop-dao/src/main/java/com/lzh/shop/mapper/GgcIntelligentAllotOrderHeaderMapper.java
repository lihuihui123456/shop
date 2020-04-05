package com.lzh.shop.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface GgcIntelligentAllotOrderHeaderMapper {
    public int findListForMapCountSize(Map filter);
}