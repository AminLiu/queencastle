package com.queencastle.dao.model;

/**
 * 小类中的具体子项
 * 
 * @author YuDongwei
 *
 */
public class PropertyDict extends BaseModel {

    private static final long serialVersionUID = 4498677741674113060L;
    private String domainId;
    private String cname;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

}
