package com.queencastle.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.dao.utils.SysDateSerializer;

/**
 * 基类，数据层
 * 
 * @author YuDongwei
 *
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -4423784462987739580L;

    /** 编号 */
    private String id;
    private long idRaw = -1;
    /** 创建时间 */
    @JsonSerialize(using = SysDateSerializer.class)
    private Date createdAt;
    /** 更新时间 */
    @JsonSerialize(using = SysDateSerializer.class)
    private Date updateAt;

    public String getId() {
        if (id == null && idRaw > 0)
            id = IdTypeHandler.encode(idRaw);
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getIdRaw() {
        return idRaw;
    }

    void setIdRaw(long idRaw) {
        this.idRaw = idRaw;
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



}
