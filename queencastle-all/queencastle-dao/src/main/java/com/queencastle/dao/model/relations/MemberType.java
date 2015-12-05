package com.queencastle.dao.model.relations;

/**
 * 群成员类型
 * 
 * @author YuDongwei
 *
 */
public enum MemberType {
    /** 群主 */
    master,
    /** 管理员 */
    admin,
    /** 成员 */
    member;
    public static MemberType getByName(String name) {
        switch (name) {
            case "master":
                return master;
            case "admin":
                return admin;
            case "member":
                return member;
            default:
                return member;
        }
    }
}
