package com.queencastle.dao.model.relations;

import com.queencastle.dao.model.BaseModel;

/**
 * 用户永久推荐二维码
 * 
 * @author YuDongwei
 *
 */
public class UserQRCode extends BaseModel {

    private static final long serialVersionUID = -2819511071522141428L;

    private String userId;
    /** 微信的URL的ticket */
    private String ticket;
    /** 放置在七牛上的地址 */
    private String img;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



}
