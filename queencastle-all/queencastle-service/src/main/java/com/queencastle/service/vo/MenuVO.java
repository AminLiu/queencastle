package com.queencastle.service.vo;

import java.util.List;

/**
 * 菜单的树形结构
 * 
 * @author YuDongwei
 *
 */
public class MenuVO {

    private String cname;
    private String ename;
    private String href;
    private List<MenuVO> children;

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

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
