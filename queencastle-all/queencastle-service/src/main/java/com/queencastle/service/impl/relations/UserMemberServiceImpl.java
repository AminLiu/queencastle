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
import com.queencastle.dao.mapper.relations.UserMemberMapper;
import com.queencastle.dao.model.relations.MemberType;
import com.queencastle.dao.model.relations.UserMember;
import com.queencastle.service.interf.relations.UserMemberService;

@Service
public class UserMemberServiceImpl implements UserMemberService {

    @Autowired
    private UserMemberMapper userMemberMapper;

    @Override
    public int insert(UserMember userMember) {
        return userMemberMapper.insert(userMember);
    }

    @Override
    public UserMember getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return userMemberMapper.getById(id);
        }
        return null;
    }

    @Override
    public List<UserMember> getByUserId(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            return userMemberMapper.getByUserId(userId);
        }
        return null;
    }

    @Override
    public List<UserMember> getByGroupId(String goupId) {
        if (StringUtils.isNoneBlank(goupId)) {
            return userMemberMapper.getByGroupId(goupId);
        }
        return null;
    }

    @Override
    public PageInfo<UserMember> getByParams(int page, int rows, Map<String, Object> queryParam) {
        PageInfo<UserMember> pageInfo = new PageInfo<UserMember>();
        pageInfo.setPage(page);
        Integer count = userMemberMapper.getUserMembersCountByParams(queryParam);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<UserMember>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);
        List<UserMember> list = userMemberMapper.getUserMembersByParams(pageable, queryParam);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public String getMaxCodeByGroupId(String goupId) {
        if (StringUtils.isNoneBlank(goupId)) {
            return userMemberMapper.getMaxCodeByGroupId(goupId);
        }
        return null;
    }

    @Override
    public List<UserMember> getUserIdByCodeAndType(String Code, MemberType type) {
        return userMemberMapper.getUserIdByCodeAndType(Code, type);
    }

    @Override
    public PageInfo<UserMember> getMyMembersByParams(int page, int rows, Map<String, Object> map) {

        PageInfo<UserMember> pageInfo = new PageInfo<UserMember>();
        pageInfo.setPage(page);
        Integer count = userMemberMapper.getMyMembersCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<UserMember>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);
        List<UserMember> list = userMemberMapper.getMyMembersByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;

    }

    @Override
    public boolean judgeMember(String userId) {
        return userMemberMapper.judgeMember(userId) > 0;
    }

    @Override
    public UserMember getByGroupIdAndUserId(String groupId, String userId) {
        if (StringUtils.isNoneBlank(groupId) && StringUtils.isNoneBlank(userId)) {
            return userMemberMapper.getByGroupIdAndUserId(groupId, userId);
        }
        return null;
    }

    @Override
    public UserMember getLatestByUserId(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            return userMemberMapper.getLatestByUserId(userId);
        }
        return null;
    }



}
