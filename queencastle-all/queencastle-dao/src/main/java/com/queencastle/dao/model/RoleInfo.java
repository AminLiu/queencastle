package com.queencastle.dao.model;

/**
 * 用户角色定义
 * 
 * @author YuDongwei
 *
 */
public class RoleInfo extends BaseModel {

    private static final long serialVersionUID = 8459032638359224380L;
    /** 角色名称 */
    private String cname;
    /** 备注信息 */
    private String memo;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }



}
