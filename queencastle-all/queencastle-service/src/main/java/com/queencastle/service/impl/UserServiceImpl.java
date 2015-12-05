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
import org.springframework.util.CollectionUtils;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.UserMapper;
import com.queencastle.dao.model.MenuInfo;
import com.queencastle.dao.model.RoleInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.UserRoleInfo;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.interf.MenuService;
import com.queencastle.service.interf.RoleMenuInfoService;
import com.queencastle.service.interf.RoleService;
import com.queencastle.service.interf.UserRoleInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.vo.MenuVO;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserRoleInfoService userRoleInfoService;
    @Autowired
    private RoleMenuInfoService roleMenuInfoService;
    @Autowired
    private RoleService roleService;

    /**
     * MD5加密用户密码
     */
    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getById(String id) {
        return userMapper.getById(id);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public PageInfo<User> getUsersByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<User> pageInfo = new PageInfo<User>();
        pageInfo.setPage(page);
        Integer count = userMapper.getUsersCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<User>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<User> list = userMapper.getUsersByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public List<User> getByUsername(String username) {
        if (StringUtils.isNoneBlank(username)) {
            return userMapper.getByUsername(username);

        }
        return null;
    }

    @Override
    public List<MenuVO> getMenuVosByUser(User user) {
        if (user == null) {
            return null;
        }
        user = getById(user.getId());
        List<MenuVO> menuVos = new ArrayList<MenuVO>();
        List<MenuInfo> firstLevel = new ArrayList<MenuInfo>();
        if (user.isAdmin()) {
            firstLevel.addAll(menuService.getMenuByParentId(IdTypeHandler.encode(1)));
            for (MenuInfo menuInfo : firstLevel) {
                MenuVO vo = new MenuVO();
                vo.setCname(menuInfo.getCname());
                vo.setEname(menuInfo.getEname());
                List<MenuInfo> secondLevel = menuService.getMenuByParentId(menuInfo.getId());
                List<MenuVO> children = new ArrayList<MenuVO>();
                if (!CollectionUtils.isEmpty(secondLevel)) {
                    for (MenuInfo child : secondLevel) {
                        MenuVO cMenu = new MenuVO();
                        cMenu.setCname(child.getCname());
                        cMenu.setEname(child.getEname());
                        cMenu.setHref(child.getHref());
                        children.add(cMenu);
                    }
                }
                vo.setChildren(children);
                menuVos.add(vo);
            }
        } else {
            String userId = user.getId();
            List<UserRoleInfo> userRoles = userRoleInfoService.getByUserId(userId);
            Set<String> roleIdSet = new HashSet<String>();
            if (!CollectionUtils.isEmpty(userRoles)) {
                for (UserRoleInfo userRole : userRoles) {
                    String roleId = userRole.getRoleId();
                    roleIdSet.add(roleId);
                    List<MenuInfo> firstChildren =
                            menuService.getByPIdAndRoleId(IdTypeHandler.encode(1), roleId);
                    if (!CollectionUtils.isEmpty(firstChildren)) {
                        firstLevel.addAll(firstChildren);
                    }
                }
                //
                for (MenuInfo menuInfo : firstLevel) {
                    MenuVO vo = new MenuVO();
                    vo.setCname(menuInfo.getCname());
                    vo.setEname(menuInfo.getEname());
                    List<MenuVO> children = new ArrayList<MenuVO>();
                    for (String roleId : roleIdSet) {
                        getSubMenuInfo(roleId, menuInfo.getId(), vo, children);
                    }
                    menuVos.add(vo);
                }


            }
        }
        return menuVos;
    }

    private void getSubMenuInfo(String roleId, String menuId, MenuVO vo, List<MenuVO> children) {
        List<MenuInfo> childrenInfo = menuService.getByPIdAndRoleId(menuId, roleId);
        if (!CollectionUtils.isEmpty(childrenInfo)) {
            for (MenuInfo child : childrenInfo) {
                MenuVO cMenu = new MenuVO();
                cMenu.setCname(child.getCname());
                cMenu.setEname(child.getEname());
                cMenu.setHref(child.getHref());
                if (!children.contains(cMenu)) {
                    children.add(cMenu);
                }
            }
        }
        vo.setChildren(children);
    }

    @Override
    public List<RoleInfo> getRolesByUser(User user) {
        if (user == null) {
            return null;
        }
        user = getById(user.getId());
        if (user.isAdmin()) {
            List<RoleInfo> list = roleService.getAllRole();
            return list;
        } else {
            List<UserRoleInfo> userRoles = userRoleInfoService.getByUserId(user.getId());
            Set<String> roleIdSet = new HashSet<String>();
            if (!CollectionUtils.isEmpty(userRoles)) {
                for (UserRoleInfo userRole : userRoles) {
                    roleIdSet.add(userRole.getRoleId());
                }
                List<RoleInfo> roles = new ArrayList<RoleInfo>();
                for (String roleId : roleIdSet) {
                    roles.add(roleService.getById(roleId));
                }
                return roles;
            }
        }
        return null;
    }

    @Override
    public boolean setUserAdmin(String userId, boolean flag) {
        if (StringUtils.isNoneBlank(userId)) {
            return userMapper.setUserAdmin(userId, flag) > 0;
        }

        return false;
    }

    @Override
    public User loadUserByOpenIdAndSource(String source, String openId) {
        if (StringUtils.isNoneBlank(source) && StringUtils.isNoneBlank(openId)) {
            return userMapper.loadUserByOpenIdAndSource(source, openId);

        }
        return null;
    }

    @Override
    public List<User> getAllUserIds() {
        return userMapper.getAllUserIds();
    }

}
