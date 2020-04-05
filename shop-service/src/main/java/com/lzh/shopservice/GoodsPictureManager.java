package com.lzh.shopservice;

import com.lzh.shopcommon.page.Page;
import com.lzh.shop.dao.GoodsPictureDao;
import com.lzh.shopentity.GoodsPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class GoodsPictureManager {
    @Autowired
    private GoodsPictureDao goodsPictureDao;
    public GoodsPicture getGoodsPicture(Long goodsPictureId) {
        return goodsPictureDao.get(goodsPictureId);
    }

    public void insert(GoodsPicture goodsPicture) {
        goodsPictureDao.insert(goodsPicture);
    }

    public Page<GoodsPicture> listGoodsPicture(Page<GoodsPicture> page, Map<String, Object> filters) {
        return goodsPictureDao.findPage(page,"", filters);
    }
    public void update(GoodsPicture goodsPicture) {
        goodsPictureDao.update(goodsPicture);
    }
}
