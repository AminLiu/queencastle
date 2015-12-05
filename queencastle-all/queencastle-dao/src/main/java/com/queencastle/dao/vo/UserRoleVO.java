package com.queencastle.dao.vo;

import java.util.List;

import com.queencastle.dao.model.RoleInfo;
import com.queencastle.dao.model.User;

/**
 * 用户的角色列表
 * 
 * @author YuDongwei
 *
 */
public class UserRoleVO {

    private User user;
    private List<RoleInfo> roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RoleInfo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleInfo> roles) {
        this.roles = roles;
    }

}
