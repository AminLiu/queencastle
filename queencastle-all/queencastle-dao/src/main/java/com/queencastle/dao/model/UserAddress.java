package com.queencastle.dao.model;

/**
 * 用户收货地址信息
 * 
 * @author YuDongwei
 *
 */
public class UserAddress extends BaseModel {

    private static final long serialVersionUID = -8292288695039274851L;
    /** 用户编号 */
    private String userId;
    /** 用户地区编号 */
    private String zoneId;
    /** 收货详细地址 */
    private String detailAddr;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }



}
