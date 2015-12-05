package com.queencastle.dao.model.area;

import com.queencastle.dao.model.BaseModel;

/**
 * 地区信息，县
 * 
 * @author YuDongwei
 *
 */
public class Area extends BaseModel {

    private static final long serialVersionUID = -3832682446840100912L;
    /** 编码 */
    private String code;
    /** 名称中文 */
    private String cname;
    /** 名称全拼 */
    private String ename;
    private String cityCode;

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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

}
