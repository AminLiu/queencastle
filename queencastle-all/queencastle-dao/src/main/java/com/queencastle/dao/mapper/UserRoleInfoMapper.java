package com.queencastle.dao.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.RoleMenuInfo;
import com.queencastle.dao.model.UserRoleInfo;

public interface UserRoleInfoMapper {
    UserRoleInfo getById(@Param("id") String id);

    int insert(UserRoleInfo userRoleInfo);

    int updateById(UserRoleInfo userRoleInfo);

    List<UserRoleInfo> getUserRoleByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);

    Integer getUserRoleCountByParams(@Param("map") Map<String, Object> map);

    List<UserRoleInfo> getByUserId(@Param("userId") String userId);

    List<RoleMenuInfo> getMenuByUserId(@Param("userId") String userId);

    void deleteByUserId(@Param("userId") String userId);

    void deleteByUserIdAndRolds(@Param("userId")String userId, @Param("roleIdSet")Set<String> roleIdSet);
}
