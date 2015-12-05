package com.queencastle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.MenuInfoMapper;
import com.queencastle.dao.model.MenuInfo;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.service.interf.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuInfoMapper menuInfoMapper;
    @Autowired
    private ObjectJedisCache menuCache;

    @Transactional
    @Override
    public int insert(MenuInfo menuInfo) {
        int rows = menuInfoMapper.insert(menuInfo);
        menuCache.setObj(menuInfo.getId(), menuInfo);
        return rows;
    }

    @Override
    public MenuInfo getById(String id) {
        MenuInfo menu = (MenuInfo) menuCache.getObj(id);
        if (menu != null) {
            return menu;
        }
        return menuInfoMapper.getById(id);
    }

    @Override
    public int updateById(MenuInfo menuInfo) {
        menuCache.clearKey(menuInfo.getId());
        return menuInfoMapper.updateById(menuInfo);
    }

    @Override
    public PageInfo<MenuInfo> getMenuByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<MenuInfo> pageInfo = new PageInfo<MenuInfo>();
        pageInfo.setPage(page);
        Integer count = menuInfoMapper.getMenuCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<MenuInfo>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<MenuInfo> list = menuInfoMapper.getMenuByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public List<MenuInfo> getMenuByParentId(String parentId) {
        if (StringUtils.isNoneBlank(parentId)) {
            return menuInfoMapper.getMenuByParentId(parentId);
        }
        return null;
    }

    @Override
    public List<MenuInfo> getByPIdAndRoleId(String parentId, String roleId) {

        if (StringUtils.isBlank(parentId) || StringUtils.isBlank(roleId)) {
            return null;
        }
        return menuInfoMapper.getByPIdAndRoleId(parentId, roleId);
    }

    @Override
    public List<MenuInfo> getMenusByPIdAndUserId(String parentId, String userId) {
        if (StringUtils.isBlank(parentId) || StringUtils.isBlank(userId)) {
            return null;
        }
        return menuInfoMapper.getMenusByPIdAndUserId(parentId, userId);
    }



}
