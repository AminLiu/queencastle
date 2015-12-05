package com.queencastle.dao.model;

/**
 * 角色和菜单的对应关系
 * 
 * @author YuDongwei
 *
 */
public class RoleMenuInfo extends BaseModel {
    private static final long serialVersionUID = 5759107628767955053L;
    /** 角色编号 */
    private String roleId;
    /** 菜单编号 */
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


}
