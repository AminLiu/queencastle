package com.queencastle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.RoleInfoMapper;
import com.queencastle.dao.model.RoleInfo;
import com.queencastle.service.interf.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Override
    public int insert(RoleInfo roleInfo) {

        return roleInfoMapper.insert(roleInfo);
    }

    @Override
    public int updateById(RoleInfo roleInfo) {

        return roleInfoMapper.updateById(roleInfo);

    }

    @Override
    public RoleInfo getById(String id) {

        return roleInfoMapper.getById(id);
    }

    @Override
    public PageInfo<RoleInfo> getRoleByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<RoleInfo> roleInfo = new PageInfo<RoleInfo>();
        roleInfo.setPage(page);
        Integer count = roleInfoMapper.getRoleCountByParams(map);
        if (count == null || count == 0) {
            roleInfo.setTotal(0);
            roleInfo.setRows(new ArrayList<RoleInfo>());
            return roleInfo;
        }
        roleInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<RoleInfo> list = roleInfoMapper.getRoleByParams(pageable, map);
        roleInfo.setRows(list);
        return roleInfo;
    }

    @Override
    public List<RoleInfo> getAllRole() {
        return roleInfoMapper.getAllRole();
    }



}
