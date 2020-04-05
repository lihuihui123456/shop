package com.lzh.shopentity;

public class Category extends IdEntity{
    private Long userId;
    private String code;//编码
    private String name;//名称
    private Long  parentId;//父级id
    private int  level;  //层级
    private int  isEndLevel;//是否末级
    private int  status;  //状态
    private int seq;  //序号

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIsEndLevel() {
        return isEndLevel;
    }

    public void setIsEndLevel(int isEndLevel) {
        this.isEndLevel = isEndLevel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
