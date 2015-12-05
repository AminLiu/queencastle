package com.queencastle.service.interf;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.RoleMenuInfo;

public interface RoleMenuInfoService {

    int insert(RoleMenuInfo roleMenuInfo);

    int updateById(RoleMenuInfo roleMenuInfo);

    List<RoleMenuInfo> getByRoleId(String roleId);

    RoleMenuInfo getById(String id);

    PageInfo<RoleMenuInfo> getRoleMenuByParams(int page, int rows, Map<String, Object> map);

    boolean saveOrUpdate(String roleId, String[] menuArray);



}
