package com.queencastle.dao.model.relations;

import com.queencastle.dao.model.BaseModel;

/**
 * 省级地区级群编码
 * 
 * @author YuDongwei
 *
 */
public class AreaGroupInfo extends BaseModel {

    private static final long serialVersionUID = 7284141700818721174L;
    private String areaId;
    private String code;
    private AreaType type;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AreaType getType() {
        return type;
    }

    public void setType(AreaType type) {
        this.type = type;
    }

}
