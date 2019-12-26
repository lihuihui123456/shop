package com.lzh.shopentity;


import java.util.Date;

/**
 * @Auther: lzh
 * @Date: 2019/11/26 10:02
 * @Description:
 */
public class GgcIntelligentAllotOrderHeader extends IdEntity {
    private String code;//智能货柜调拨单号
    /**
     * 调拨单code规则:调拨补货
     */
    public static final String IL_HEADER_BH = "DBPL";
    private Long fromWareHouseId;//调拨出库源库 调拨入库源货柜 计划单时候无用
    private Long toWareHouseId;//调拨出库目标货位  调拨入库目标仓库
    private Integer goodsKindNum;//商品种类
    private Integer goodsTotalNum;//商品总数量
    private Integer status = 0;//状态
    /**
     * 当前计划状态
     **/
    public static final Integer STATUS_PLAN = -1;
    /**
     * 当前业务初始状态
     **/
    public static final Integer STATUS_INIT = 0;
    /**
     * 当前业务已完成或者已取消
     **/
    public static final Integer STATUS_FINISH = 1;

    private Integer printNumber;//打印次数
    private Date lastPrintTime;//上次打印时间
    private Date createTime;//创建时间
    private Long createPartyId;
    private Long createUserId;//创建人
    private String transferNotes;       //调拨备注
    private Integer businessType;       //单据类型

    /**
     * 单据类型
     **/
    public static final Integer BUSINESS_TYPE_PLAN_OUT = 1;//调拨计划出库单
    private Double totalMoney;//总金额
    //非持久化
    private String znhgName;       //智能货柜名称
    private String customerName;       //客户名称
    private String fromWareHouseName;       //源仓库名称
    private String createPartyName;       //主体名称
    private String createUserName;       //用户名称

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getFromWareHouseId() {
        return fromWareHouseId;
    }

    public void setFromWareHouseId(Long fromWareHouseId) {
        this.fromWareHouseId = fromWareHouseId;
    }

    public Long getToWareHouseId() {
        return toWareHouseId;
    }

    public void setToWareHouseId(Long toWareHouseId) {
        this.toWareHouseId = toWareHouseId;
    }

    public Integer getGoodsKindNum() {
        return goodsKindNum;
    }

    public void setGoodsKindNum(Integer goodsKindNum) {
        this.goodsKindNum = goodsKindNum;
    }

    public Integer getGoodsTotalNum() {
        return goodsTotalNum;
    }

    public void setGoodsTotalNum(Integer goodsTotalNum) {
        this.goodsTotalNum = goodsTotalNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static Integer getStatusInit() {
        return STATUS_INIT;
    }

    public static Integer getStatusFinish() {
        return STATUS_FINISH;
    }

    public Integer getPrintNumber() {
        return printNumber;
    }

    public void setPrintNumber(Integer printNumber) {
        this.printNumber = printNumber;
    }

    public Date getLastPrintTime() {
        return lastPrintTime;
    }

    public void setLastPrintTime(Date lastPrintTime) {
        this.lastPrintTime = lastPrintTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatePartyId() {
        return createPartyId;
    }

    public void setCreatePartyId(Long createPartyId) {
        this.createPartyId = createPartyId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getTransferNotes() {
        return transferNotes;
    }

    public void setTransferNotes(String transferNotes) {
        this.transferNotes = transferNotes;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getZnhgName() {
        return znhgName;
    }

    public void setZnhgName(String znhgName) {
        this.znhgName = znhgName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFromWareHouseName() {
        return fromWareHouseName;
    }

    public void setFromWareHouseName(String fromWareHouseName) {
        this.fromWareHouseName = fromWareHouseName;
    }

    public String getCreatePartyName() {
        return createPartyName;
    }

    public void setCreatePartyName(String createPartyName) {
        this.createPartyName = createPartyName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
