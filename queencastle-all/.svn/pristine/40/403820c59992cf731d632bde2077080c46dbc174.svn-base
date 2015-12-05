package com.queencastle.web.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.queencastle.dao.model.relations.GroupLevel;
import com.queencastle.dao.utils.SysDateSerializer;

public class ManagerVO {
    private String id;
    private String userId;
    private String userName;
    /** 名称 */
    private String cname;
    /** 级别 */
    private GroupLevel level;
    /** 创建时间 */
    @JsonSerialize(using = SysDateSerializer.class)
    private Date createdAt;
    /** 更新时间 */
    @JsonSerialize(using = SysDateSerializer.class)
    private Date updateAt;
    private String areaId;
    private String areaName;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public GroupLevel getLevel() {
        return level;
    }

    public void setLevel(GroupLevel level) {
        this.level = level;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


}
