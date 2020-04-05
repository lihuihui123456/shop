package com.lzh.shopentity;

import java.util.Date;

/**
 * 订单明细
 */
public class SalesOrder extends IdEntity {
    private Long orderHeaderId;     // 订单头ID
    private Long referenceOrderId;   // 退货时关联订货单编号
    private int orderStatus;          // 单据状态
    // 商品
    private long goodsId;             // 商品id
    private String goodsUnit;          //价格记录到商品信息
    private double discountFee;       //折扣的优惠  //优惠到总金额
    private double totalMoney;          //总金额【单价*数量-优惠】
    private long integral;              //积分
    private int minPackageQuantity;     // 最小包装量
    private Integer orderingQuantity;        //订货：购数量， 退货：退货数量
    private Date orderingTime;            //订退货时间（下单时间）
    private Integer confirmsQuantity;        //订退货的审核发货确认数量
    private Date confirmsTime;            //审核发货时间
    private Date finalTime;               // 订、退单结束时间
    private Date updateTime;              //时间戳
    private boolean outOfStock;           //缺货标记
    private boolean payment;              //是否需要结算
    private double coupon; //优惠卷
    private String note;  //（订单备注）
    private int isDelete;//0:正常    1：删除

    public Long getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderHeaderId(Long orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public Long getReferenceOrderId() {
        return referenceOrderId;
    }

    public void setReferenceOrderId(Long referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public double getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(double discountFee) {
        this.discountFee = discountFee;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public long getIntegral() {
        return integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }

    public int getMinPackageQuantity() {
        return minPackageQuantity;
    }

    public void setMinPackageQuantity(int minPackageQuantity) {
        this.minPackageQuantity = minPackageQuantity;
    }

    public Integer getOrderingQuantity() {
        return orderingQuantity;
    }

    public void setOrderingQuantity(Integer orderingQuantity) {
        this.orderingQuantity = orderingQuantity;
    }

    public Date getOrderingTime() {
        return orderingTime;
    }

    public void setOrderingTime(Date orderingTime) {
        this.orderingTime = orderingTime;
    }

    public Integer getConfirmsQuantity() {
        return confirmsQuantity;
    }

    public void setConfirmsQuantity(Integer confirmsQuantity) {
        this.confirmsQuantity = confirmsQuantity;
    }

    public Date getConfirmsTime() {
        return confirmsTime;
    }

    public void setConfirmsTime(Date confirmsTime) {
        this.confirmsTime = confirmsTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public double getCoupon() {
        return coupon;
    }

    public void setCoupon(double coupon) {
        this.coupon = coupon;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
