package com.lzh.shopweb.controller;

import com.lzh.shopservice.GgcStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public ModelAndView selectUUser(Model model){
        int data = ggcStockService.findData();
        Map<String, Object> data1 = new HashMap<>(2);
        data1.put("name", "springboot Freemarker");
        data1.put("now", new Date());
        //如何配置试图解析器
        return new ModelAndView("index", data1);
    }
}