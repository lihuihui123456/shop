package com.lzh.shopservice;

import com.lzh.shopdao.dao.GgcIntelligentAllotOrderHeaderDao;
import com.lzh.shopdao.dao.UserReadDao;
import com.lzh.shopentity.User;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: lzh
 * @Date: 2019/12/20 19:57
 * @Description:
 */
@Service
public class GgcStockService {
    @Autowired
    private GgcIntelligentAllotOrderHeaderDao ggcIntelligentAllotOrderHeaderDao;
    @Autowired
    private UserReadDao userReadDao;
    public int findDataByMapper() {
        int listForMapCountSize = ggcIntelligentAllotOrderHeaderDao.findListForMapCountSize(new HashMap());
        return listForMapCountSize;
    }

    public List findDataByDao() {
        List list = ggcIntelligentAllotOrderHeaderDao.findList("findListForMapCountSize", new HashMap());
        return list;
    }
    public User findUser(Long id) {
        return userReadDao.get(id) ;
    }
}
