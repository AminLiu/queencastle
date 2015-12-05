package com.queencastle.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.MenuInfo;

public interface MenuInfoMapper {

    MenuInfo getById(@Param("id") String id);

    int insert(MenuInfo menuInfo);

    int updateById(MenuInfo menuInfo);

    List<MenuInfo> getMenuByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);

    Integer getMenuCountByParams(@Param("map") Map<String, Object> map);

    List<MenuInfo> getMenuByParentId(@Param("parentId") String parentId);

    List<MenuInfo> getAllFirstLevelMenu();

    List<MenuInfo> getByPIdAndRoleId(@Param("parentId") String parentId,
            @Param("roleId") String roleId);

    List<MenuInfo> getMenusByPIdAndUserId(@Param("parentId") String parentId,
            @Param("userId") String userId);

}
