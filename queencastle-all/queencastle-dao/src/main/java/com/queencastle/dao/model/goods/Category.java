package com.queencastle.dao.model.goods;

import com.queencastle.dao.model.BaseModel;

/**
 * 产品种类
 * 
 * @author YuDongwei
 *
 */
public class Category extends BaseModel {

    private static final long serialVersionUID = 6723976476914907869L;
    /** 种类名称中文 */
    private String cname;
    /** 种类名称英文 */
    private String ename;

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
