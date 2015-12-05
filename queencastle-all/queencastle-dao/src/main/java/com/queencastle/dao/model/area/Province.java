package com.queencastle.dao.model.area;

import com.queencastle.dao.model.BaseModel;

/**
 * 地区信息，省
 * 
 * @author YuDongwei
 *
 */
public class Province extends BaseModel {

    private static final long serialVersionUID = 4345183293362704080L;
    /** 编码 */
    private String code;
    /** 名称中文 */
    private String cname;
    /** 名称全拼 */
    private String ename;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }



}
