package com.lzh.shopweb.controller;

import com.lzh.shopservice.GgcStockService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: lzh
 * @Date: 2019/12/23 10:51
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class TestController {
    @Autowired
    private GgcStockService ggcStockService;

    @RequestMapping("test")
    @ResponseBody
    public int selectUUser(){
        int data = ggcStockService.findData();
        return data;
    }
}