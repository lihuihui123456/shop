package com.lzh.shopentity;

import java.util.Date;

/**
 * 订单头
 */
public class SalesOrderHeader extends IdEntity{
    private long sellerId;          //卖家ID
    private long buyerId;           //买家ID
    private Date createDate;       //创建时间戳
    private Date updateTime;       //最后一次更新时间
    private double totalMoney;                //总金额
    private int totalAmount;                  //总数量
    private double orderingTotalMoney;			//订单总金额（下订单时的总金额，以后不会再改变）
    private int orderType;                    //订单类型 10 表示订货单 20 表示退货单
    private long receiveAddressId;                    //收货地址
    private Double   deliveryFee; 			  // 运费
    private String note;                      //退货信息 （订单附言）
    private String message;                   //提示信息
    private Integer settleType;                   //结算类型
    private int printTimes;                   //打印次数
    private Boolean allOrderClosed;       //是否所有订单已关闭  false:未关闭 true：全部关闭
    private int isDelete;//0:正常    1：删除
    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getOrderingTotalMoney() {
        return orderingTotalMoney;
    }

    public void setOrderingTotalMoney(double orderingTotalMoney) {
        this.orderingTotalMoney = orderingTotalMoney;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public long getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(long receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public int getPrintTimes() {
        return printTimes;
    }

    public void setPrintTimes(int printTimes) {
        this.printTimes = printTimes;
    }

    public Boolean getAllOrderClosed() {
        return allOrderClosed;
    }

    public void setAllOrderClosed(Boolean allOrderClosed) {
        this.allOrderClosed = allOrderClosed;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
