package com.queencastle.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.RoleMenuInfoMapper;
import com.queencastle.dao.model.MenuInfo;
import com.queencastle.dao.model.RoleMenuInfo;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.interf.MenuService;
import com.queencastle.service.interf.RoleMenuInfoService;

@Service
public class RoleMenuInfoServiceImpl implements RoleMenuInfoService {
    @Autowired
    private RoleMenuInfoMapper roleMenuInfoMapper;
    @Autowired
    private MenuService menuService;

    @Override
    public int insert(RoleMenuInfo roleMenuInfo) {
        return roleMenuInfoMapper.insert(roleMenuInfo);
    }

    @Override
    public int updateById(RoleMenuInfo roleMenuInfo) {
        return roleMenuInfoMapper.updateById(roleMenuInfo);
    }

    @Override
    public RoleMenuInfo getById(String id) {
        return roleMenuInfoMapper.getById(id);
    }

    @Override
    public PageInfo<RoleMenuInfo> getRoleMenuByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<RoleMenuInfo> roleMenuInfo = new PageInfo<RoleMenuInfo>();
        roleMenuInfo.setPage(page);
        Integer count = roleMenuInfoMapper.getRoleMenuCountByParams(map);
        if (count == null || count == 0) {
            roleMenuInfo.setTotal(0);
            roleMenuInfo.setRows(new ArrayList<RoleMenuInfo>());
            return roleMenuInfo;
        }
        roleMenuInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<RoleMenuInfo> list = roleMenuInfoMapper.getRoleMenuByParams(pageable, map);
        roleMenuInfo.setRows(list);
        return roleMenuInfo;
    }

    @Override
    public List<RoleMenuInfo> getByRoleId(String roleId) {
        if (StringUtils.isNoneBlank(roleId)) {
            return roleMenuInfoMapper.getByRoleId(roleId);
        }
        return null;
    }

    @Override
    public boolean saveOrUpdate(String roleId, String[] menuArray) {
        // 应该被选中的节点
        Set<String> menuIdSet = new HashSet<String>();
        for (String menuId : menuArray) {
            menuIdSet.add(menuId);
            MenuInfo menu = menuService.getById(menuId);
            String pid = menu.getParentId();
            String zeroId = IdTypeHandler.encode(0);
            // 如果父节点不是零，需要把该节点的父节点找出
            if (!StringUtils.equalsIgnoreCase(pid, zeroId)) {
                menuIdSet.add(pid);
            }
        }
        //
        Set<String> oldSet = new HashSet<String>();
        List<RoleMenuInfo> list = getByRoleId(roleId);
        for (RoleMenuInfo info : list) {
            oldSet.add(info.getMenuId());
        }
        Set<String> tmpSet = new HashSet<String>();
        // 删除
        tmpSet.addAll(oldSet);
        tmpSet.removeAll(menuIdSet);
        if (tmpSet.size() > 0) {
            deleteByRoleIdAndMenus(roleId, tmpSet);
        }
        // 新增
        tmpSet.clear();
        tmpSet.addAll(menuIdSet);
        tmpSet.removeAll(oldSet);
        if (tmpSet.size() > 0) {
            for (String menuId : tmpSet) {
                RoleMenuInfo info = new RoleMenuInfo();
                info.setMenuId(menuId);
                info.setRoleId(roleId);
                insert(info);
            }
            tmpSet.clear();
        }


        return false;
    }

    private void deleteByRoleIdAndMenus(String roleId, Set<String> menuIdSet) {
        roleMenuInfoMapper.deleteByRoleIdAndMenus(roleId, menuIdSet);
    }
}
