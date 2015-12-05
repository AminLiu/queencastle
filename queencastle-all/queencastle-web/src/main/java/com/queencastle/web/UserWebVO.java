package com.queencastle.web;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.queencastle.dao.utils.SysDateSerializer;

public class UserWebVO {
    /********************************** 账号最核心的基本信息 ******************************/
    private String id;
    private String phone;
    private String username;
    private String source;
    private String outNick;
    private boolean admin;
    @JsonSerialize(using = SysDateSerializer.class)
    private Date createdAt;
    @JsonSerialize(using = SysDateSerializer.class)
    private Date updateAt;
    private String weixinNo;
    /********************************** 账号地域信息信息 ******************************/

    private String cityCode;
    private String cityName;
    private String provinceCode;
    private String provinceName;

    /********************************* 账号的群成员信息 ***********************************/
    /** 所在群编号 */
    private String groupIds;
    /** 所在群名称 */
    private String memberInfos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOutNick() {
        return outNick;
    }

    public void setOutNick(String outNick) {
        this.outNick = outNick;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getMemberInfos() {
        return memberInfos;
    }

    public void setMemberInfos(String memberInfos) {
        this.memberInfos = memberInfos;
    }

    public String getWeixinNo() {
        return weixinNo;
    }

    public void setWeixinNo(String weixinNo) {
        this.weixinNo = weixinNo;
    }



}
