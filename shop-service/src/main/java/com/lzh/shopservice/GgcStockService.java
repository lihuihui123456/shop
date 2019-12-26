package com.lzh.shopservice;

import com.lzh.shopdao.dao.GgcIntelligentAllotOrderHeaderDao;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Auther: lzh
 * @Date: 2019/12/20 19:57
 * @Description:
 */
@Service
public class GgcStockService {
    @Autowired
    private GgcIntelligentAllotOrderHeaderDao ggcIntelligentAllotOrderHeaderDao;
    public int findData() {
        int listForMapCountSize = ggcIntelligentAllotOrderHeaderDao.findListForMapCountSize(new HashMap());
        return listForMapCountSize;
    }
}
