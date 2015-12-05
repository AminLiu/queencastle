package com.queencastle.dao.vo;

import java.io.Serializable;

import com.queencastle.dao.model.User;

public class UserSession implements Serializable {

    private static final long serialVersionUID = -8991242264726043326L;
    /** 当前用户 */
    private User user;
    /** 访问时间 */
    private Long accessTime;

    public Long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Long accessTime) {
        this.accessTime = accessTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
