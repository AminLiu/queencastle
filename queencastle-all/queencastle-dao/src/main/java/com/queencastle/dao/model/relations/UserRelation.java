package com.queencastle.dao.model.relations;

import com.queencastle.dao.model.BaseModel;

/**
 * 用户之间的关系，上下级关系，根据需求这个层级暂时不限，如果parentId为0表示是系统管理级别，和省级代理是平级<br>
 * 需要关注的地方：一：避免循环，二、层级持久化以后的迭代最好到缓存里，放置关系链
 * 
 * @author YuDongwei
 *
 */
public class UserRelation extends BaseModel {

    private static final long serialVersionUID = 3036054997242110244L;
    /** 用户编号 */
    private String userId;
    /** 上级编号 */
    private String parentId;
    /** 关系 */
    private RelationType type;
    /** 所在层级 */
    private Integer layer;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public RelationType getType() {
        return type;
    }

    public void setType(RelationType type) {
        this.type = type;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

}
