package com.queencastle.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.RoleInfo;

public interface RoleInfoMapper {
    RoleInfo getById(@Param("id") String id);

    int insert(RoleInfo roleInfo);

    int updateById(RoleInfo roleInfo);

    List<RoleInfo> getRoleByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);

    Integer getRoleCountByParams(@Param("map") Map<String, Object> map);

    List<RoleInfo> getAllRole();



}
