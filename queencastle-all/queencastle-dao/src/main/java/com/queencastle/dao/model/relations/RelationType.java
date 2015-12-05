package com.queencastle.dao.model.relations;

/**
 * 关系类型，userId表示当前用户，这里的推荐，其实是被parentId推荐
 * 
 * @author YuDongwei
 *
 */
public enum RelationType {
    /** 推荐 */
    recommend,
    /** 上下层级 */
    hierarchy;

    public static RelationType getByName(String name) {
        switch (name) {
            case "recommend":
                return recommend;
            case "hierarchy":
                return hierarchy;
            default:
                return recommend;
        }
    }

}
