package com.queencastle.service.interf.relations;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.relations.RelationType;
import com.queencastle.dao.model.relations.UserRelation;

public interface UserRelationService {

    int insert(UserRelation userRelation);

    UserRelation getById(String id);

    /**
     * 根据上级节点和节点类型查找下一级子节点
     * 
     * @param parentId
     * @param type
     * @return
     */
    List<UserRelation> getByParentIdAndType(String parentId, RelationType type);

    boolean checkAndInsert(UserRelation userRelation);

    List<String> getUserIdByParentId(String parentId);

    PageInfo<UserRelation> getAgencyByParams(int page, int rows, Map<String, Object> map);

    UserRelation getByUserId(String userId);


}
