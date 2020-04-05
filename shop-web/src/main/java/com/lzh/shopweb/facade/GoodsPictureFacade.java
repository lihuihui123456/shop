package com.lzh.shopweb.facade;

import com.lzh.shopservice.GoodsPictureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsPictureFacade {
    @Autowired
    private GoodsPictureManager goodsPictureManager;
}
