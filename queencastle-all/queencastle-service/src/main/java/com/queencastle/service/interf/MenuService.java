package com.queencastle.service.interf;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.MenuInfo;

public interface MenuService {

    int insert(MenuInfo menuInfo);

    MenuInfo getById(String id);

    int updateById(MenuInfo menuInfo);

    PageInfo<MenuInfo> getMenuByParams(int page, int rows, Map<String, Object> map);

    List<MenuInfo> getMenuByParentId(String parentId);

    List<MenuInfo> getByPIdAndRoleId(String parentId, String roleId);

    List<MenuInfo> getMenusByPIdAndUserId(String parentId, String userId);

}
