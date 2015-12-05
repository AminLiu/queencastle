package com.queencastle.service.interf.relations;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.relations.MemberType;
import com.queencastle.dao.model.relations.UserMember;

public interface UserMemberService {
    int insert(UserMember userMember);

    UserMember getById(String id);

    /** 根据用户编号获取信息,同时在各个群的人才有数据 */
    List<UserMember> getByUserId(String userId);

    UserMember getLatestByUserId(String userId);

    /** 获取指定组内的所有成员 */
    List<UserMember> getByGroupId(String goupId);

    PageInfo<UserMember> getByParams(int page, int rows, Map<String, Object> queryParam);

    String getMaxCodeByGroupId(String goupId);

    List<UserMember> getUserIdByCodeAndType(String Code, MemberType type);

    PageInfo<UserMember> getMyMembersByParams(int page, int rows, Map<String, Object> map);

    boolean judgeMember(String userId);

    UserMember getByGroupIdAndUserId(String groupId, String userId);



}
