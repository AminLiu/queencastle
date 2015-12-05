package com.queencastle.service.interf.relations;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.relations.GroupType;
import com.queencastle.dao.model.relations.MemberType;
import com.queencastle.dao.model.relations.UserGroup;

public interface UserGroupService {
    int insert(UserGroup userGroup);

    UserGroup getById(String id);

    List<UserGroup> getByCode(String code);

    PageInfo<UserGroup> getUserGroupsByParams(int page, int rows, Map<String, Object> map);

    UserGroup getByGroupCode(String groupCode);

    boolean updateById(UserGroup userGroup);

    List<String> getCodeListByBase(String base);

    List<UserGroup> getAllUsableGroup();

    List<UserGroup> getByMasterId(String masterId, GroupType type);

    boolean handleSelfBuildGroup(String parentId, String userId);

    boolean configUserMember(String userId, MemberType type, String groupId);



    boolean selfBuildGroup(String cname, String img, String profile, User user);



}
