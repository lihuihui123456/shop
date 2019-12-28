package com.lzh.shopweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzh.shopentity.User;
import com.lzh.shopservice.GgcStockService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lzh
 * @Date: 2019/12/23 10:51
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class TestController {
    @Autowired
    private GgcStockService ggcStockService;

    @RequestMapping("test")
    public String selectUUser(Model model){
        int data = ggcStockService.findDataByMapper();
        model.addAttribute("name", "springboot Freemarker");
        return "index";
    }
    @RequestMapping("test5")
    public String selectUUser5(Model model){
        ggcStockService.findDataByDao();
        model.addAttribute("name", "springboot Freemarker");
        return "index";
    }
    @RequestMapping("test6")
    public String selectUUser6(Model model){
        User user = ggcStockService.findUser(123L);
        model.addAttribute("name", user.getName());
        return "index";
    }
    @RequestMapping("test3")
    @ResponseBody  //此标签表示返回json等数据格式 不需要解析视图解析器
    public String selectUUser3(Model model){
        int data = ggcStockService.findDataByMapper();
        model.addAttribute("name", "springboot Freemarker");
        return "index";
    }
    @RequestMapping("test1")
    @ResponseBody  //此标签表示返回json等数据格式 不需要解析视图解析器
    public void selectUUser1(HttpServletResponse response) throws Exception{
        JSONObject json = new JSONObject();
        String code = "0000";
        String msg = "ok";
        String totalAmount = "15555";
        Map<String ,Object> map = new HashMap<>();
        try {

        } catch (Exception e) {
            code = "0001";
            msg = e.getMessage();
        }
        json.put("code", code);
        json.put("msg", msg);
        double total = Double.parseDouble(totalAmount);
        totalAmount = String.format("%.2f", total);
        json.put("totalAmount", totalAmount);
        json.put("totalAmount_td", String.format("%.2f",map.get("totalAmount_td")));
        json.put("totalNum_td", map.get("totalNum_td"));
        this.response(response, json.toString());
    }


   
    
    
    protected void response(HttpServletResponse response, String responseMsg) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(responseMsg);
        out.flush();
        out.close();
    }
}