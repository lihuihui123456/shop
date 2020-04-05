package com.lzh.shopweb.vo;


public class ReceiveAddressView{
    private Long id;
    private Integer version = 10;
    private long partyId;
    private long areaId;
    private String consignee; //收货人
    private String address; //收货地址
    private String tel; //联系电话
    private String  defaultAddressStr; // 默认
    private boolean defaultAddress;

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDefaultAddressStr() {
        return defaultAddressStr;
    }

    public void setDefaultAddressStr(String defaultAddressStr) {
        this.defaultAddressStr = defaultAddressStr;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
