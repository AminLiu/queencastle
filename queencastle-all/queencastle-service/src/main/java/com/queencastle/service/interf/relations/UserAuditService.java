package com.queencastle.service.interf.relations;

import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.relations.UserAudit;

public interface UserAuditService {
    int insert(UserAudit userAudit);

    UserAudit getById(String id);

    UserAudit getByUserId(String applyUserId);

    PageInfo<UserAudit> getByParams(int page, int rows, Map<String, Object> map);

    int updateStatusAndReason(UserAudit userAudit);

    void updateOrInsert(User applyUser);
}
