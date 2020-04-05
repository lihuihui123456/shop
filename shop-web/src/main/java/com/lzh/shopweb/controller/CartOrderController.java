package com.lzh.shopweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzh.shopcommon.MobileConstant;
import com.lzh.shopcommon.page.Page;
import com.lzh.shopcommon.util1.UID;
import com.lzh.shopentity.CartOrder;
import com.lzh.shopweb.facade.CartOrderFacade;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车管理
 */
@Controller
@RequestMapping("/cartOrder")
public class CartOrderController extends BaseController{

    @Autowired
    private CartOrderFacade cartOrderFacade;
    /**
     * 购物车列表
     *
     * @param userId   用户ID
     * @param pageNo     分页页数
     * @param pageSize     分页大小
     * @return 购物车列表
     */
    @GetMapping("list")
    @ResponseBody  //此标签表示返回json等数据格式 不需要解析视图解析器
    public void list(HttpServletRequest request, HttpServletResponse response,
                     Integer userId,
                     @RequestParam(defaultValue = "0") Integer showType,
                     @RequestParam(defaultValue = "1") Integer pageNo,
                     @RequestParam(defaultValue = "10") Integer pageSize) {
        try{
            //获取购物车信息
            Map filter=new HashMap();
            filter.put("userId",userId);
            Page<CartOrder> page = initPage(request);
            Page<CartOrder> pageVo = cartOrderFacade.listCartOrder(page, filter);
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
     * 加入购物车
     * <p>
     *
     * @param userId 用户ID
     * @param body   加入信息，{ goodsId：xxx, quantity: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 操作结果
     */
    @PostMapping("addCart")
    public void addCart(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
            //加入购物车清单
            JSONObject jsonObject = JSONObject.parseObject(body);
            Long  goodsId = Long.parseLong(jsonObject.get("goodsId").toString());
            Integer quantity = Integer.parseInt(jsonObject.get("quantity").toString());
            CartOrder cartOrder=new CartOrder();
            cartOrder.setId(UID.next());
            cartOrder.setVersion(10);
            cartOrder.setUserId(userId);
            cartOrder.setGoodsId(goodsId);
            cartOrder.setQuantity(quantity);
            cartOrder.setCreateTime(new Date());
            cartOrderFacade.insert(cartOrder);
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }

    /**
     * 修改购物车
     * <p>
     *
     * @param userId 用户ID
     * @param body   加入信息，{ goodsId：xxx, quantity: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 操作结果
     */
    @PostMapping("updateCart")
    public void updateCart(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
            //修改购物车清单
            JSONObject jsonObject = JSONObject.parseObject(body);
            Long  cartOrderId = Long.parseLong(jsonObject.get("cartOrderId").toString());
            Integer quantity = Integer.parseInt(jsonObject.get("quantity").toString());
            CartOrder cartOrder = cartOrderFacade.getCartOrder(cartOrderId);
            cartOrder.setQuantity(quantity);
            cartOrderFacade.update(cartOrder);
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }
    /**
     * 删除购物清单支持批量
     *
     * @param userId 用户ID
     * @param body   购物车清单信息，{ cartOrderIds：xxx }
     * @return 操作结果
     */
    @PostMapping("delete")
    public void delete(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
        JSONObject jsonObject = JSONObject.parseObject(body);
        String[] cartOrderIdsStr = jsonObject.get("cartOrderIds").toString().split(",");
        Long[] cartOrderIds = (Long[]) ConvertUtils.convert(cartOrderIdsStr, Long.class);
        cartOrderFacade.batchDelete(Arrays.asList(cartOrderIds));
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }
}
