package com.queencastle.dao.model.relations;

/**
 * 群类型
 * 
 * @author YuDongwei
 *
 */
public enum GroupType {
    /** 系统默认的建群 */
    system,
    /** 个人自建群 */
    individual;


    public static GroupType getByName(String name) {
        switch (name) {
            case "system":
                return system;
            case "individual":
                return individual;
            default:
                return system;
        }

    }
}
