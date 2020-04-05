package com.lzh.shopentity;

import java.util.Date;

public class GoodsPicture extends IdEntity {
    private Long  goodsId;//商品id
    private String  url;  //图片地址
    private int  seq;//顺序号
    private int  isMain;  //是否为主图
    private Date createTime;  //创建时间

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getIsMain() {
        return isMain;
    }

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
