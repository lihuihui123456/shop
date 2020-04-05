package com.lzh.shopweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzh.shopcommon.MobileConstant;
import com.lzh.shopcommon.page.Page;
import com.lzh.shopentity.User;
import com.lzh.shopservice.UserManager;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseController {
    private UserManager userManager;
    public User getCurrentUser() {
        //获取用户id进行识别
        Long userId=123456L;
        return userManager.getUser(userId);
    }
    protected void response(HttpServletResponse response, String responseMsg) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(responseMsg);
        out.flush();
        out.close();
    }
    /**
     * 应答信息的返回
     *
     * @param response
     * @param
     * @throws IOException
     */
    protected void response(HttpServletResponse response, String code, String msg,String data){
        JSONObject json = new JSONObject();
        JSONObject json1 = new JSONObject();
        json1.put("code", code);
        json1.put("msg", msg);
        json.put("status", json1);
        json.put("data", data);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write(json.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 应答信息的返回
     *
     * @param response
     * @param
     * @throws IOException
     */
    protected void response(HttpServletResponse response, String code, String msg){
        JSONObject json = new JSONObject();
        JSONObject json1 = new JSONObject();
        json1.put("code", code);
        json1.put("msg", msg);
        json.put("status", json1);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write(json.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected <T> Page<T> initPage(HttpServletRequest request){
        Page<T> page = new Page<T>();
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if(pageNo==null || "".equals(pageNo)){
            pageNo="1";
        }
        page.setPageNo(Integer.valueOf(pageNo));
        if(StringUtils.isBlank(pageSize)){
            page.setPageSize(MobileConstant.PageSize._page_size);
        }else{
            page.setPageSize(Integer.valueOf(pageSize));
        }
        return page;
    }
}
