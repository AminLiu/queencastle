package com.queencastle.dao.mapper.relations;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.relations.UserAudit;

public interface UserAuditMapper {
    int insert(UserAudit userAudit);

    UserAudit getById(@Param("id") String id);

    UserAudit getByUserId(@Param("applyUserId") String applyUserId);

    Integer getUserAuditsCountByParams(@Param("map") Map<String, Object> map);

    List<UserAudit> getUserAuditsByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);

    int updateStatusAndReason(UserAudit userAudit);
}
