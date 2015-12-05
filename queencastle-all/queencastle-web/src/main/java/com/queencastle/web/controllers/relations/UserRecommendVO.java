package com.queencastle.web.controllers.relations;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.queencastle.dao.utils.SysDateSerializer;

public class UserRecommendVO {

    private String id;
    private String userId;
    private String userNick;
    private String parentId;
    private String parentNick;
    @JsonSerialize(using = SysDateSerializer.class)
    private Date createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentNick() {
        return parentNick;
    }

    public void setParentNick(String parentNick) {
        this.parentNick = parentNick;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
