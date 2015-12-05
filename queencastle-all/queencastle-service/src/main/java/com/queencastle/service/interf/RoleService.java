package com.queencastle.service.interf;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.RoleInfo;

public interface RoleService {

    int insert(RoleInfo roleInfo);

    int updateById(RoleInfo roleInfo);

    RoleInfo getById(String id);

    PageInfo<RoleInfo> getRoleByParams(int page, int rows, Map<String, Object> map);

    List<RoleInfo> getAllRole();


}
