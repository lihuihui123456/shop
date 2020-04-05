package com.lzh.shopentity;

import java.util.Date;

public class Goods extends IdEntity{
    // 基本信息
    private String name;                // 名称
    private String standardName;                // 标准名称
    private Long categoryId;     // 分类id
    private String goodsLable;          // 分类
    private String goodsBrand;            // 品牌
    private String goodsDesc;            	// 商品详情
    private Long userId;            	//所属商家
    private Long goodsPictureId; 			// 图片id
    private Long goodsUnitId;           // 计量单位ID
    private String goodsUnit;          	// 单位 ---> 计量单位
    // 状态信息
    private int status;                 // 商品管理状态，参照IGoodsStatus
    // 价格信息
    private Double orderPrice;          // 结算价(￥)
    private Double memberPrice;         //原价
    // 时间信息
    private Date addTime;               // 上架时间
    private Date updateTime;           	// 更新时间戳
   //非持久化属性
    private String url;//图片地址
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsLable() {
        return goodsLable;
    }

    public void setGoodsLable(String goodsLable) {
        this.goodsLable = goodsLable;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsPictureId() {
        return goodsPictureId;
    }

    public void setGoodsPictureId(Long goodsPictureId) {
        this.goodsPictureId = goodsPictureId;
    }

    public Long getGoodsUnitId() {
        return goodsUnitId;
    }

    public void setGoodsUnitId(Long goodsUnitId) {
        this.goodsUnitId = goodsUnitId;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
