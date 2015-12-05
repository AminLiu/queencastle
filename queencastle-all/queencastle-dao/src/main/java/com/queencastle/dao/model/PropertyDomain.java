package com.queencastle.dao.model;

/**
 * 分类中的大类
 * 
 * @author YuDongwei
 *
 */
public class PropertyDomain extends BaseModel {

    private static final long serialVersionUID = 8654618994577550355L;
    private String cname;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

}
