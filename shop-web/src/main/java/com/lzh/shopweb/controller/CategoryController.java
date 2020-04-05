package com.lzh.shopweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzh.shopcommon.MobileConstant;
import com.lzh.shopcommon.page.Page;
import com.lzh.shopcommon.util1.UID;
import com.lzh.shopentity.Category;
import com.lzh.shopweb.facade.CategoryFacade;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 分类管理
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
    @Autowired
    private CategoryFacade categoryFacade;
    /**
     * 分类列表
     *
     * @param userId   用户ID
     * @param pageNo     分页页数
     * @param pageSize     分页大小
     * @return 分类列表
     */
    @GetMapping("list")
    @ResponseBody  //此标签表示返回json等数据格式 不需要解析视图解析器
    public void list(HttpServletRequest request, HttpServletResponse response,
                     Integer userId,
                     Integer level,
                     @RequestParam(defaultValue = "0") Integer showType,
                     @RequestParam(defaultValue = "1") Integer pageNo,
                     @RequestParam(defaultValue = "10") Integer pageSize) {
        try{
            //获取列表信息
            Map filter=new HashMap();
            filter.put("userId",userId);
            filter.put("level",level);
            Page<Category> page = initPage(request);
            Page<Category> pageVo = categoryFacade.listCategory(page, filter);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("list",pageVo.getResult());
            jsonObject.put("pageNo",pageVo.getPageNo());
            jsonObject.put("pageSize",pageVo.getPageSize());
            JSONObject data = new JSONObject();
            data.put("data",jsonObject);
            this.response(response, data.toJSONString());
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }
    /**
     * 新增分类
     * <p>
     *
     * @param userId 用户ID
     * @param body   加入信息，{ goodsId：xxx, quantity: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 操作结果
     */
    @PostMapping("add")
    public void addCategory(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{

            JSONObject jsonObject = JSONObject.parseObject(body);
            Long  parentId = Long.parseLong(jsonObject.get("parentId").toString());
            Integer status = Integer.parseInt(jsonObject.get("status").toString());
            Integer seq = Integer.parseInt(jsonObject.get("seq").toString());
            Integer isEndLevel = Integer.parseInt(jsonObject.get("isEndLevel").toString());
            Integer level = Integer.parseInt(jsonObject.get("level").toString());
            String code=jsonObject.get("code").toString();
            String name=jsonObject.get("name").toString();
            Category category=new Category();
            category.setId(UID.next());
            category.setVersion(10);
            category.setUserId(userId);
            category.setParentId(parentId);
            category.setCode(code);
            category.setName(name);
            category.setLevel(level);
            category.setIsEndLevel(isEndLevel);
            category.setSeq(seq);
            category.setStatus(status);
            categoryFacade.insert(category);
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }

    /**
     * 修改分类
     * <p>
     *
     * @param userId 用户ID
     * @param body   加入信息，{ goodsId：xxx, quantity: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 操作结果
     */
    @PostMapping("update")
    public void updateCategory(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
            JSONObject jsonObject = JSONObject.parseObject(body);
            Long  categoryId = Long.parseLong(jsonObject.get("categoryId").toString());
            Long  parentId = Long.parseLong(jsonObject.get("parentId").toString());
            Integer status = Integer.parseInt(jsonObject.get("status").toString());
            Integer seq = Integer.parseInt(jsonObject.get("seq").toString());
            Integer isEndLevel = Integer.parseInt(jsonObject.get("isEndLevel").toString());
            Integer level = Integer.parseInt(jsonObject.get("level").toString());
            String code=jsonObject.get("code").toString();
            String name=jsonObject.get("name").toString();
            Category category=categoryFacade.getCategory(categoryId);
            category.setUserId(userId);
            category.setParentId(parentId);
            category.setCode(code);
            category.setName(name);
            category.setLevel(level);
            category.setIsEndLevel(isEndLevel);
            category.setSeq(seq);
            category.setStatus(status);
            categoryFacade.update(category);
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }
    /**
     * 删除分类
     *
     * @param userId 用户ID
     * @param body   分类信息，{ categoryIds：xxx }
     * @return 操作结果
     */
    @PostMapping("delete")
    public void delete(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
            JSONObject jsonObject = JSONObject.parseObject(body);
            String[] categoryIdsStr = jsonObject.get("categoryIds").toString().split(",");
            Long[] categoryIds = (Long[]) ConvertUtils.convert(categoryIdsStr, Long.class);
            categoryFacade.batchDelete(Arrays.asList(categoryIds));
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }
}

