package com.queencastle.dao.model;

/**
 * 用户签到，这个涉及到并发的问题，缓存先处理
 * 
 * @author YuDongwei
 *
 */
public class UserCheckIn extends BaseModel {

    private static final long serialVersionUID = 6872166060785464802L;
    /** 用户编号 */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
