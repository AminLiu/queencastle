package com.queencastle.dao.vo;

import java.util.List;

import com.queencastle.dao.model.MenuInfo;
import com.queencastle.dao.model.RoleInfo;

/**
 * 岗位的菜单列表
 * 
 * @author YuDongwei
 *
 */
public class RoleMenuVO {

    private RoleInfo role;
    private List<MenuInfo> menus;

    public RoleInfo getRole() {
        return role;
    }

    public void setRole(RoleInfo role) {
        this.role = role;
    }

    public List<MenuInfo> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuInfo> menus) {
        this.menus = menus;
    }

}
