package com.lzh.shopweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzh.shopcommon.MobileConstant;
import com.lzh.shopcommon.page.Page;
import com.lzh.shopcommon.util1.UID;
import com.lzh.shopentity.Goods;
import com.lzh.shopweb.facade.GoodsFacade;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/goods")
/**
 * 商品信息管理
 */
public class GoodsController extends BaseController{
    @Autowired
    private GoodsFacade goodsFacade;
    /**
     * 商品列表
     *
     * @param userId   用户ID
     * @param pageNo     分页页数
     * @param pageSize     分页大小
     * @return 商品列表
     */
    @GetMapping("list")
    @ResponseBody  //此标签表示返回json等数据格式 不需要解析视图解析器
    public void list(HttpServletRequest request, HttpServletResponse response,
                     Integer userId,
                     Integer categoryId,
                     @RequestParam(defaultValue = "0") Integer showType,
                     @RequestParam(defaultValue = "1") Integer pageNo,
                     @RequestParam(defaultValue = "10") Integer pageSize) {
        try{
            //获取商品信息
            Map filter=new HashMap();
            filter.put("userId",666666);
            filter.put("categoryId",11111);
            Page<Goods> page = initPage(request);
            Page<Goods> pageVo = goodsFacade.listGoods(page, filter);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("list",pageVo.getResult());
            jsonObject.put("pageNo",pageVo.getPageNo());
            jsonObject.put("pageSize",pageVo.getPageSize());
            jsonObject.put("code",200);
            JSONObject data = new JSONObject();
            data.put("data",jsonObject);
            this.response(response, data.toJSONString());
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }
    /**
     * 添加商品
     * <p>
     *
     * @param userId 用户ID
     * @param body   加入信息，{ goodsId：xxx, quantity: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 操作结果
     */
    @PostMapping("addGoods")
    public void addGoods(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
            JSONObject jsonObject = JSONObject.parseObject(body);
            Long  categoryId = Long.parseLong(jsonObject.get("categoryId").toString());
            String name = jsonObject.get("name").toString();
            //添加商品
             Goods  goods =new Goods();
             goods.setId(UID.next());
             goods.setVersion(10);
             goods.setCategoryId(categoryId);
             goods.setName(name);
             goods.setOrderPrice(10d);
            goods.setMemberPrice(20d);
            goods.setAddTime(new Date());
            goodsFacade.insert(goods);
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }

    /**
     * 修改商品
     * <p>
     *
     * @param userId 用户ID
     * @param body   加入信息，{ goodsId：xxx, quantity: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 操作结果
     */
    @PostMapping("updateCart")
    public void updateCart(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
            JSONObject jsonObject = JSONObject.parseObject(body);
            Long  goodsId = Long.parseLong(jsonObject.get("goodsId").toString());
            Long  categoryId = Long.parseLong(jsonObject.get("categoryId").toString());
            String name = jsonObject.get("name").toString();
            Goods goods = goodsFacade.getGoods(goodsId);
            goods.setCategoryId(categoryId);
            goods.setName(name);
            goodsFacade.update(goods);
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }
    /**
     * 删除商品
     *
     * @param userId 用户ID
     * @param body   待删除商品id信息，{ goodsIds：xxx }
     * @return 操作结果
     */
    @PostMapping("delete")
    public void delete(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
            JSONObject jsonObject = JSONObject.parseObject(body);
            String[] goodsIdsStr = jsonObject.get("goodsIds").toString().split(",");
            Long[] goodsIds = (Long[]) ConvertUtils.convert(goodsIdsStr, Long.class);
            goodsFacade.batchDelete(Arrays.asList(goodsIds));
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }
}
