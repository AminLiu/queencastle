package com.queencastle.dao.model;

/**
 * 全国省市县三级信息
 * 
 * @author YuDongwei
 *
 */
public class ZoneInfo extends BaseModel {

    private static final long serialVersionUID = -4184053164849369908L;
    /** 名称 */
    private String cname;
    /** 父级节点，全国定为0 */
    private String parentId;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


}
