package com.queencastle.service.impl.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.relations.UserAuditMapper;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.relations.AuditStatus;
import com.queencastle.dao.model.relations.UserAudit;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.relations.UserAuditService;

@Service
public class UserAuditServiceImpl implements UserAuditService {
    @Autowired
    private UserAuditMapper userAuditMapper;
    @Autowired
    private UserService userService;

    @Override
    public int insert(UserAudit userAudit) {
        return userAuditMapper.insert(userAudit);
    }

    @Override
    public UserAudit getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return userAuditMapper.getById(id);
        }
        return null;
    }

    @Override
    public UserAudit getByUserId(String applyUserId) {
        if (StringUtils.isNoneBlank(applyUserId)) {
            return userAuditMapper.getByUserId(applyUserId);
        }
        return null;
    }

    @Override
    public PageInfo<UserAudit> getByParams(int page, int rows, Map<String, Object> map) {

        PageInfo<UserAudit> pageInfo = new PageInfo<UserAudit>();
        pageInfo.setPage(page);
        Integer count = userAuditMapper.getUserAuditsCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<UserAudit>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<UserAudit> list = userAuditMapper.getUserAuditsByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;

    }

    @Override
    public int updateStatusAndReason(UserAudit userAudit) {
        return userAuditMapper.updateStatusAndReason(userAudit);
    }

    @Override
    public void updateOrInsert(User applyUser) {
        UserAudit userAudit = getByUserId(applyUser.getId());
        String auditUserId = IdTypeHandler.encode(1L);
        User auditUser = userService.getById(auditUserId);
        if (userAudit == null) {
            userAudit = new UserAudit();
            userAudit.setApplyUser(applyUser);
            userAudit.setAuditStatus(AuditStatus.success);
            userAudit.setReason("agree");
            userAudit.setAuditUser(auditUser);
            insert(userAudit);
        } else {
            userAudit.setAuditStatus(AuditStatus.success);
            userAudit.setReason("agree");
            userAudit.setAuditUser(auditUser);
            updateStatusAndReason(userAudit);
        }



    }

}
