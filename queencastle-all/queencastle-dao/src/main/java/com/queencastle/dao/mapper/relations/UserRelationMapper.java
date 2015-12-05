package com.queencastle.dao.mapper.relations;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.relations.RelationType;
import com.queencastle.dao.model.relations.UserRelation;

public interface UserRelationMapper {

    List<UserRelation> getByParentIdAndType(@Param("parentId") String parentId,
            @Param("type") RelationType type);

    int insert(UserRelation userRelation);

    void getById(@Param("id") String id);

    Integer getCntByUserId(@Param("userId") String userId);

    Integer getByParentId(@Param("parentId") String pid);

    Integer getLevelByUserId(@Param("userId") String pid);

    List<String> getUserIdByParentId(@Param("parentId") String parentId);

    List<UserRelation> getAgencyByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);

    Integer getAgencyCountByParams(@Param("queryParam") Map<String, Object> queryParam);

    UserRelation getByUserId(@Param("userId") String userId);

}
