package com.queencastle.dao.model;

/**
 * 用户和角色的关联表
 * 
 * @author YuDongwei
 *
 */
public class UserRoleInfo extends BaseModel {

    private static final long serialVersionUID = -7550555828744705207L;
    /** 用户编号 */
    private String userId;
    /** 角色编号 */
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


}
