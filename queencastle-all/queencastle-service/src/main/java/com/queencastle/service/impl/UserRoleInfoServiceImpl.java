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
import com.queencastle.dao.mapper.UserRoleInfoMapper;
import com.queencastle.dao.model.UserRoleInfo;
import com.queencastle.service.interf.UserRoleInfoService;

@Service
public class UserRoleInfoServiceImpl implements UserRoleInfoService {

    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;
    @Autowired
    private RoleMenuInfoMapper roleMenuInfoMapper;

    @Override
    public int insert(UserRoleInfo userRoleInfo) {
        return userRoleInfoMapper.insert(userRoleInfo);
    }

    @Override
    public UserRoleInfo getById(String id) {
        return userRoleInfoMapper.getById(id);
    }

    @Override
    public int updateById(UserRoleInfo userRoleInfo) {
        return userRoleInfoMapper.updateById(userRoleInfo);
    }

    @Override
    public PageInfo<UserRoleInfo> getUserRoleByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<UserRoleInfo> pageInfo = new PageInfo<UserRoleInfo>();
        pageInfo.setPage(page);
        Integer count = userRoleInfoMapper.getUserRoleCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<UserRoleInfo>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<UserRoleInfo> list = userRoleInfoMapper.getUserRoleByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public List<UserRoleInfo> getByUserId(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            return userRoleInfoMapper.getByUserId(userId);
        }
        return null;
    }

    @Override
    public void saveOrUpdate(String userId, String[] roleArray) {
        List<UserRoleInfo> userRoles = getByUserId(userId);
        Set<String> exisitedIds = new HashSet<String>();
        for (UserRoleInfo ur : userRoles) {
            exisitedIds.add(ur.getRoleId());
        }
        Set<String> roleSet = new HashSet<String>();
        for (String roleId : roleArray) {
            roleSet.add(roleId);
        }
        // 删除
        Set<String> tmpSet = new HashSet<String>();
        tmpSet.addAll(exisitedIds);
        tmpSet.removeAll(roleSet);
        if (tmpSet.size() > 0) {
            deleteByUserIdAndRolds(userId, tmpSet);
        }
        // 新增
        tmpSet.clear();
        tmpSet.addAll(roleSet);
        tmpSet.removeAll(exisitedIds);
        if (tmpSet.size() > 0) {
            for (String roleId : tmpSet) {
                UserRoleInfo info = new UserRoleInfo();
                info.setUserId(userId);
                info.setRoleId(roleId);
                insert(info);
            }
            tmpSet.clear();
        }


    }

    private void deleteByUserIdAndRolds(String userId, Set<String> roleIdSet) {
        userRoleInfoMapper.deleteByUserIdAndRolds(userId, roleIdSet);
    }

    @Override
    public int deleteByUserId(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            userRoleInfoMapper.deleteByUserId(userId);
        }
        return 0;
    }



}
