package com.queencastle.dao.model;

/**
 * 用户在系统中可以点击的菜单,菜单是一个树形结构，最顶层为零，暂定为三级菜单，以后根据需要扩展
 * 
 * @author YuDongwei
 *
 */
public class MenuInfo extends BaseModel {

    private static final long serialVersionUID = -4021290197203204L;
    /** 父级菜单编号 */
    private String parentId;
    /** 菜单的中文简写 */
    private String cname;
    /** 菜单的英文简写 */
    private String ename;
    /** 在子菜单中的排序 */
    private int rank;
    /** 跳转的页面 */
    private String href;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }



}
