package com.queencastle.dao.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.RoleMenuInfo;

public interface RoleMenuInfoMapper {

    RoleMenuInfo getById(@Param("id") String id);

    int insert(RoleMenuInfo roleMenuInfo);

    int updateById(RoleMenuInfo roleMenuInfo);

    List<RoleMenuInfo> getRoleMenuByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);

    Integer getRoleMenuCountByParams(@Param("map") Map<String, Object> map);

    List<RoleMenuInfo> getByRoleId(@Param("roleId") String roleId);

    void deleteByRoleIdAndMenus(@Param("roleId") String roleId,
            @Param("menuIdSet") Set<String> menuIdSet);



}
