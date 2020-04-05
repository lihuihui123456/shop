package com.lzh.shopweb.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzh.shopcommon.MobileConstant;
import com.lzh.shopcommon.page.Page;
import com.lzh.shopentity.SalesOrder;
import com.lzh.shopentity.SalesOrderHeader;
import com.lzh.shopweb.facade.SalesOrderFacade;
import com.lzh.shopweb.facade.UserFacade;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 订单服务
 *
 * <p>
 * 订单状态：
 * 101 订单生成，未支付；102，下单后未支付用户取消；103，下单后未支付超时系统自动取消
 * 201 支付完成，商家未发货；202，订单生产，已付款未发货，但是退款取消；
 * 301 商家发货，用户未确认；
 * 401 用户确认收货； 402 用户没有确认收货超过一定时间，系统自动确认收货；
 *
 * <p>
 * 用户操作：
 * 当101用户未付款时，此时用户可以进行的操作是取消订单，或者付款操作
 * 当201支付完成而商家未发货时，此时用户可以取消订单并申请退款
 * 当301商家已发货时，此时用户可以有确认收货的操作
 * 当401用户确认收货以后，此时用户可以进行的操作是删除订单，评价商品，或者再次购买
 * 当402系统自动确认收货以后，此时用户可以删除订单，评价商品，或者再次购买
 *
 * <p>
 * 注意：目前不支持订单退货和售后服务
 */
@RestController
@RequestMapping("/sales/order")
public class SalesOrderController extends BaseController{
    private final Log logger = LogFactory.getLog(SalesOrderController.class);

    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private SalesOrderFacade salesOrderFacade;

    /**
     * 订单列表
     *
     * @param userId   用户ID
     * @param showType 订单信息：
     *                 0，全部订单；
     *                 1，待付款；
     *                 2，待发货；
     *                 3，待收货；
     *                 4，待评价。
     * @param page     分页页数
     * @param size     分页大小
     * @return 订单列表
     */
    @GetMapping("list")
    @ResponseBody  //此标签表示返回json等数据格式 不需要解析视图解析器
    public void list(HttpServletRequest request,HttpServletResponse response,
                        Integer userId,
                       @RequestParam(defaultValue = "0") Integer showType,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        try{
        //获取订单头
        Map filter=new HashMap();
        filter.put("userId",userId);
        Page<SalesOrderHeader> page = initPage(request);
        Page<SalesOrderHeader> pageVo = salesOrderFacade.listSalesOrderHeader(page, filter);
        JSONArray jsonArrayHeader=new JSONArray();
        for (SalesOrderHeader orderHeader : pageVo.getResult()) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("salesOrderHeader",orderHeader);
            Map<String, Object> filtersOrder = new HashMap<>();
            filtersOrder.put("orderHeaderId", orderHeader.getId());
    /*        orderVo.put("orderSn", order.getOrderSn());
            orderVo.put("actualPrice", order.getActualPrice());
            orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
            orderVo.put("handleOption", OrderUtil.build(order));*/
            List<SalesOrder> salesOrders = salesOrderFacade.listSalesOrder(filtersOrder);
            JSONArray jsonArray=new JSONArray();
            for (SalesOrder orderDetail : salesOrders) {
                JSONObject jsonObjectDetail=new JSONObject();
                jsonObjectDetail.put("id", orderDetail.getId());
                jsonObjectDetail.put("goodsId", orderDetail.getGoodsId());
                jsonArray.add(jsonObjectDetail);
            }
            jsonObject.put("salesOrderArray", jsonArray);
            jsonArrayHeader.add(jsonObject);
            }
            JSONObject data = new JSONObject();
            data.put("data",jsonArrayHeader);
            this.response(response, data.toJSONString());
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }

    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("detail")
    @ResponseBody  //此标签表示返回json等数据格式 不需要解析视图解析器
    public void detail(HttpServletRequest request,HttpServletResponse response,Integer userId, @NotNull Long orderHeaderId) throws Exception{
        // 订单信息
        SalesOrderHeader orderHeader = salesOrderFacade.getSalesOrderHeader(orderHeaderId);
        Map<String, Object> filtersOrder = new HashMap<>();
        filtersOrder.put("orderHeaderId", orderHeader.getId());
    /*        orderVo.put("orderSn", order.getOrderSn());
            orderVo.put("actualPrice", order.getActualPrice());
            orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
            orderVo.put("handleOption", OrderUtil.build(order));*/
        List<SalesOrder> salesOrders = salesOrderFacade.listSalesOrder(filtersOrder);
        JSONObject json=new JSONObject();
        json.put("orderHeader", orderHeader);
        json.put("salesOrders", salesOrders);
        JSONObject data = new JSONObject();
        data.put("data",json);
        this.response(response, data.toJSONString());
    }

    /**
     * 提交订单
     * <p>
     * 1. 创建订单表项和订单商品表项;
     * 2. 购物车清空;
     * 3. 优惠券设置已用;
     * 4. 商品货品库存减少;
     * 5. 如果是团购商品，则创建团购活动表项。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @PostMapping("submit")
    public void submit(HttpServletRequest request,HttpServletResponse response,Long userId, @RequestBody String body) {
        try{
            //获取购物车清单和收货信息
        JSONObject jsonObject = JSONObject.parseObject(body);
        String[] cartOrderIdsStr = jsonObject.get("cartOrderIds").toString().split(",");
        Long[] cartOrderIds = (Long[]) ConvertUtils.convert(cartOrderIdsStr, Long.class);
        String receiveAddressId = jsonObject.get("receiveAddressId").toString();
        String message = jsonObject.get("message").toString();
        Double coupon = Double.parseDouble(jsonObject.get("message").toString());
        salesOrderFacade.createSalesOrder(Arrays.asList(cartOrderIds),Long.parseLong(receiveAddressId),message,coupon);
            this.response(response, MobileConstant.MemberStockError._ok, MobileConstant.MemberStockError._ok_msg);
        }catch (Exception e) {
            e.printStackTrace();
            this.response(response, MobileConstant.Error._error, MobileConstant.Error._error_msg);
        }
    }

    /**
     * 取消订单
     * <p>
     * 1. 检测当前订单是否能够取消；
     * 2. 设置订单取消状态；
     * 3. 商品货品库存恢复；
     * 4. TODO 优惠券；
     * 5. TODO 团购活动。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @PostMapping("cancel")
    public void cancel(Integer userId, @RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        Long orderHeaderId = Long.parseLong(jsonObject.get("orderHeaderId").toString());
        salesOrderFacade.cancelSalesOrder(orderHeaderId);
   }

    /**
     * 删除订单
     * <p>
     * 1. 检测当前订单是否可以删除；
     * 2. 删除订单。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderHeaderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("delete")
    public void delete(Long userId, @RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        Long orderHeaderId = Long.parseLong(jsonObject.get("orderHeaderId").toString());
        salesOrderFacade.cancelSalesOrder(orderHeaderId);
    }
//
//    /**
//     * 付款订单的预支付会话标识
//     * <p>
//     * 1. 检测当前订单是否能够付款
//     * 2. 微信商户平台返回支付订单ID
//     * 3. 设置订单付款状态
//     *
//     * @param userId 用户ID
//     * @param body   订单信息，{ orderId：xxx }
//     * @return 支付订单ID
//     */
//    @PostMapping("prepay")
//    public Object prepay(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
//        if (orderId == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        LitemallOrder order = orderService.findById(orderId);
//        if (order == null) {
//            return ResponseUtil.badArgumentValue();
//        }
//        if (!order.getUserId().equals(userId)) {
//            return ResponseUtil.badArgumentValue();
//        }
//
//        // 检测是否能够取消
//        OrderHandleOption handleOption = OrderUtil.build(order);
//        if (!handleOption.isPay()) {
//            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能支付");
//        }
//
//        LitemallUser user = userService.findById(userId);
//        String openid = user.getWeixinOpenid();
//        if (openid == null) {
//            return ResponseUtil.fail(AUTH_OPENID_UNACCESS, "订单不能支付");
//        }
//        WxPayMpOrderResult result = null;
//        try {
//            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//            orderRequest.setOutTradeNo(order.getOrderSn());
//            orderRequest.setOpenid(openid);
//            orderRequest.setBody("订单：" + order.getOrderSn());
//            // 元转成分
//            Integer fee = 0;
//            BigDecimal actualPrice = order.getActualPrice();
//            fee = actualPrice.multiply(new BigDecimal(100)).intValue();
//            orderRequest.setTotalFee(fee);
//            orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));
//
//            result = wxPayService.createOrder(orderRequest);
//
//            //缓存prepayID用于后续模版通知
//            String prepayId = result.getPackageValue();
//            prepayId = prepayId.replace("prepay_id=", "");
//            LitemallUserFormid userFormid = new LitemallUserFormid();
//            userFormid.setOpenid(user.getWeixinOpenid());
//            userFormid.setFormid(prepayId);
//            userFormid.setIsprepay(true);
//            userFormid.setUseamount(3);
//            userFormid.setExpireTime(LocalDateTime.now().plusDays(7));
//            formIdService.addUserFormid(userFormid);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseUtil.fail(ORDER_PAY_FAIL, "订单不能支付");
//        }
//
//        if (orderService.updateWithOptimisticLocker(order) == 0) {
//            return ResponseUtil.updatedDateExpired();
//        }
//        return ResponseUtil.ok(result);
//    }
//
//    /**
//     * 微信付款成功或失败回调接口
//     * <p>
//     * 1. 检测当前订单是否是付款状态;
//     * 2. 设置订单付款成功状态相关信息;
//     * 3. 响应微信商户平台.
//     * <p>
//     *  注意，这里pay-notify是示例地址，建议开发者应该设立一个隐蔽的回调地址
//     *
//     * @param request 请求内容
//     * @param response 响应内容
//     * @return 操作结果
//     */
//    @PostMapping("pay-notify")
//    public Object payNotify(HttpServletRequest request, HttpServletResponse response) {
//        String xmlResult = null;
//        try {
//            xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return WxPayNotifyResponse.fail(e.getMessage());
//        }
//
//        WxPayOrderNotifyResult result = null;
//        try {
//            result = wxPayService.parseOrderNotifyResult(xmlResult);
//        } catch (WxPayException e) {
//            e.printStackTrace();
//            return WxPayNotifyResponse.fail(e.getMessage());
//        }
//
//        logger.info("处理腾讯支付平台的订单支付");
//        logger.info(result);
//
//        String orderSn = result.getOutTradeNo();
//        String payId = result.getTransactionId();
//
//        // 分转化成元
//        String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
//        LitemallOrder order = orderService.findBySn(orderSn);
//        if (order == null) {
//            return WxPayNotifyResponse.fail("订单不存在 sn=" + orderSn);
//        }
//
//        // 检查这个订单是否已经处理过
//        if (OrderUtil.isPayStatus(order) && order.getPayId() != null) {
//            return WxPayNotifyResponse.success("订单已经处理成功!");
//        }
//
//        // 检查支付订单金额
//        if (!totalFee.equals(order.getActualPrice().toString())) {
//            return WxPayNotifyResponse.fail(order.getOrderSn() + " : 支付金额不符合 totalFee=" + totalFee);
//        }
//
//        order.setPayId(payId);
//        order.setPayTime(LocalDateTime.now());
//        order.setOrderStatus(OrderUtil.STATUS_PAY);
//        if (orderService.updateWithOptimisticLocker(order) == 0) {
//            // 这里可能存在这样一个问题，用户支付和系统自动取消订单发生在同时
//            // 如果数据库首先因为系统自动取消订单而更新了订单状态；
//            // 此时用户支付完成回调这里也要更新数据库，而由于乐观锁机制这里的更新会失败
//            // 因此，这里会重新读取数据库检查状态是否是订单自动取消，如果是则更新成支付状态。
//            order = orderService.findBySn(orderSn);
//            int updated = 0;
//            if (OrderUtil.isAutoCancelStatus(order)) {
//                order.setPayId(payId);
//                order.setPayTime(LocalDateTime.now());
//                order.setOrderStatus(OrderUtil.STATUS_PAY);
//                updated = orderService.updateWithOptimisticLocker(order);
//            }
//
//            // 如果updated是0，那么数据库更新失败
//            if (updated == 0) {
//                return WxPayNotifyResponse.fail("更新数据已失效");
//            }
//        }
//
//        //  支付成功，有团购信息，更新团购信息
//        LitemallGroupon groupon = grouponService.queryByOrderId(order.getId());
//        if (groupon != null) {
//            LitemallGrouponRules grouponRules = grouponRulesService.queryById(groupon.getRulesId());
//
//            //仅当发起者才创建分享图片
//            if (groupon.getGrouponId() == 0) {
//                String url = qCodeService.createGrouponShareImage(grouponRules.getGoodsName(), grouponRules.getPicUrl(), groupon);
//                groupon.setShareUrl(url);
//            }
//            groupon.setPayed(true);
//            if (grouponService.updateById(groupon) == 0) {
//                return WxPayNotifyResponse.fail("更新数据已失效");
//            }
//        }
//
//        //TODO 发送邮件和短信通知，这里采用异步发送
//        // 订单支付成功以后，会发送短信给用户，以及发送邮件给管理员
//        notifyService.notifyMail("新订单通知", order.toString());
//        // 这里微信的短信平台对参数长度有限制，所以将订单号只截取后6位
//        notifyService.notifySmsTemplateSync(order.getMobile(), NotifyType.PAY_SUCCEED, new String[]{orderSn.substring(8, 14)});
//
//        // 请依据自己的模版消息配置更改参数
//        String[] parms = new String[]{
//                order.getOrderSn(),
//                order.getOrderPrice().toString(),
//                DateTimeUtil.getDateTimeDisplayString(order.getAddTime()),
//                order.getConsignee(),
//                order.getMobile(),
//                order.getAddress()
//        };
//
//        notifyService.notifyWxTemplate(result.getOpenid(), NotifyType.PAY_SUCCEED, parms, "pages/index/index?orderId=" + order.getId());
//
//        return WxPayNotifyResponse.success("处理成功!");
//    }
//
//    /**
//     * 订单申请退款
//     * <p>
//     * 1. 检测当前订单是否能够退款；
//     * 2. 设置订单申请退款状态。
//     *
//     * @param userId 用户ID
//     * @param body   订单信息，{ orderId：xxx }
//     * @return 订单退款操作结果
//     */
//    @PostMapping("refund")
//    public Object refund(@LoginUser Integer userId, @RequestBody String body) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
//        if (orderId == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        LitemallOrder order = orderService.findById(orderId);
//        if (order == null) {
//            return ResponseUtil.badArgument();
//        }
//        if (!order.getUserId().equals(userId)) {
//            return ResponseUtil.badArgumentValue();
//        }
//
//        OrderHandleOption handleOption = OrderUtil.build(order);
//        if (!handleOption.isRefund()) {
//            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能取消");
//        }
//
//        // 设置订单申请退款状态
//        order.setOrderStatus(OrderUtil.STATUS_REFUND);
//        if (orderService.updateWithOptimisticLocker(order) == 0) {
//            return ResponseUtil.updatedDateExpired();
//        }
//
//        //TODO 发送邮件和短信通知，这里采用异步发送
//        // 有用户申请退款，邮件通知运营人员
//        notifyService.notifyMail("退款申请", order.toString());
//
//        return ResponseUtil.ok();
//    }
//
//    /**
//     * 确认收货
//     * <p>
//     * 1. 检测当前订单是否能够确认收货；
//     * 2. 设置订单确认收货状态。
//     *
//     * @param userId 用户ID
//     * @param body   订单信息，{ orderId：xxx }
//     * @return 订单操作结果
//     */
//    @PostMapping("confirm")
//    public Object confirm(@LoginUser Integer userId, @RequestBody String body) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
//        if (orderId == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        LitemallOrder order = orderService.findById(orderId);
//        if (order == null) {
//            return ResponseUtil.badArgument();
//        }
//        if (!order.getUserId().equals(userId)) {
//            return ResponseUtil.badArgumentValue();
//        }
//
//        OrderHandleOption handleOption = OrderUtil.build(order);
//        if (!handleOption.isConfirm()) {
//            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能确认收货");
//        }
//
//        Short comments = orderGoodsService.getComments(orderId);
//        order.setComments(comments);
//
//        order.setOrderStatus(OrderUtil.STATUS_CONFIRM);
//        order.setConfirmTime(LocalDateTime.now());
//        if (orderService.updateWithOptimisticLocker(order) == 0) {
//            return ResponseUtil.updatedDateExpired();
//        }
//        return ResponseUtil.ok();
//    }
//

//
//    /**
//     * 待评价订单商品信息
//     *
//     * @param userId  用户ID
//     * @param orderId 订单ID
//     * @param goodsId 商品ID
//     * @return 待评价订单商品信息
//     */
//    @GetMapping("goods")
//    public Object goods(@LoginUser Integer userId,
//                        @NotNull Integer orderId,
//                        @NotNull Integer goodsId) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//
//        List<LitemallOrderGoods> orderGoodsList = orderGoodsService.findByOidAndGid(orderId, goodsId);
//        int size = orderGoodsList.size();
//
//        Assert.state(size < 2, "存在多个符合条件的订单商品");
//
//        if (size == 0) {
//            return ResponseUtil.badArgumentValue();
//        }
//
//        LitemallOrderGoods orderGoods = orderGoodsList.get(0);
//        return ResponseUtil.ok(orderGoods);
//    }
//
//    /**
//     * 评价订单商品
//     * <p>
//     * 确认商品收货或者系统自动确认商品收货后7天内可以评价，过期不能评价。
//     *
//     * @param userId 用户ID
//     * @param body   订单信息，{ orderId：xxx }
//     * @return 订单操作结果
//     */
//    @PostMapping("comment")
//    public Object comment(@LoginUser Integer userId, @RequestBody String body) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//
//        Integer orderGoodsId = JacksonUtil.parseInteger(body, "orderGoodsId");
//        if (orderGoodsId == null) {
//            return ResponseUtil.badArgument();
//        }
//        LitemallOrderGoods orderGoods = orderGoodsService.findById(orderGoodsId);
//        if (orderGoods == null) {
//            return ResponseUtil.badArgumentValue();
//        }
//        Integer orderId = orderGoods.getOrderId();
//        LitemallOrder order = orderService.findById(orderId);
//        if (order == null) {
//            return ResponseUtil.badArgumentValue();
//        }
//        Short orderStatus = order.getOrderStatus();
//        if (!OrderUtil.isConfirmStatus(order) && !OrderUtil.isAutoConfirmStatus(order)) {
//            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "当前商品不能评价");
//        }
//        if (!order.getUserId().equals(userId)) {
//            return ResponseUtil.fail(ORDER_INVALID, "当前商品不属于用户");
//        }
//        Integer commentId = orderGoods.getComment();
//        if (commentId == -1) {
//            return ResponseUtil.fail(ORDER_COMMENT_EXPIRED, "当前商品评价时间已经过期");
//        }
//        if (commentId != 0) {
//            return ResponseUtil.fail(ORDER_COMMENTED, "订单商品已评价");
//        }
//
//        String content = JacksonUtil.parseString(body, "content");
//        Integer star = JacksonUtil.parseInteger(body, "star");
//        if (star == null || star < 0 || star > 5) {
//            return ResponseUtil.badArgumentValue();
//        }
//        Boolean hasPicture = JacksonUtil.parseBoolean(body, "hasPicture");
//        List<String> picUrls = JacksonUtil.parseStringList(body, "picUrls");
//        if (hasPicture == null || !hasPicture) {
//            picUrls = new ArrayList<>(0);
//        }
//
//        // 1. 创建评价
//        LitemallComment comment = new LitemallComment();
//        comment.setUserId(userId);
//        comment.setType((byte) 0);
//        comment.setValueId(orderGoods.getGoodsId());
//        comment.setStar(star.shortValue());
//        comment.setContent(content);
//        comment.setHasPicture(hasPicture);
//        comment.setPicUrls(picUrls.toArray(new String[]{}));
//        commentService.save(comment);
//
//        // 2. 更新订单商品的评价列表
//        orderGoods.setComment(comment.getId());
//        orderGoodsService.updateById(orderGoods);
//
//        // 3. 更新订单中未评价的订单商品可评价数量
//        Short commentCount = order.getComments();
//        if (commentCount > 0) {
//            commentCount--;
//        }
//        order.setComments(commentCount);
//        orderService.updateWithOptimisticLocker(order);
//
//        return ResponseUtil.ok();
//    }

}