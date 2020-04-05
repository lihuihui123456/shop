package com.lzh.shopentity;

import java.util.Date;

/**
 * 购物车清单
 */
public class CartOrder extends IdEntity{
    // 商品
    private Long userId;            //用户
    private Long goodsId;             // 商品id
    private String goodsUnit;          //价格记录到商品信息
    private int minPackageQuantity;     // 最小包装量
    private Integer quantity;        //数量
    private Date createTime;            //创建时间
    private Date updateTime;              //时间戳


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public int getMinPackageQuantity() {
        return minPackageQuantity;
    }

    public void setMinPackageQuantity(int minPackageQuantity) {
        this.minPackageQuantity = minPackageQuantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
