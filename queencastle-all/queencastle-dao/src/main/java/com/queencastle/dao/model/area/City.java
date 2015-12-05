package com.queencastle.dao.model.area;

import com.queencastle.dao.model.BaseModel;

/**
 * 地区信息，市
 * 
 * @author YuDongwei
 *
 */
public class City extends BaseModel {

    private static final long serialVersionUID = 5270350824161704258L;

    /** 编码 */
    private String code;
    /** 名称中文 */
    private String cname;
    /** 名称全拼 */
    private String ename;
    private String provinceCode;

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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

}
