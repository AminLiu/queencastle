package com.queencastle.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.TreeInfo;
import com.queencastle.dao.model.MenuInfo;
import com.queencastle.dao.model.RoleInfo;
import com.queencastle.dao.model.RoleMenuInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.UserDetailInfo;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.model.relations.AreaGroupInfo;
import com.queencastle.dao.model.relations.AreaType;
import com.queencastle.dao.model.relations.GroupType;
import com.queencastle.dao.model.relations.MemberType;
import com.queencastle.dao.model.relations.UserGroup;
import com.queencastle.dao.model.relations.UserMember;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.dao.vo.SimpleUser;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.interf.MenuService;
import com.queencastle.service.interf.RoleMenuInfoService;
import com.queencastle.service.interf.RoleService;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserRoleInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.relations.AreaGroupService;
import com.queencastle.service.interf.relations.UserGroupService;
import com.queencastle.service.interf.relations.UserMemberService;
import com.queencastle.service.sessions.PermissionContext;
import com.queencastle.web.UserWebVO;

@Controller
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleInfoService userRoleInfoService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuInfoService roleMenuInfoService;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private UserDetailInfoService userDetailInfoService;
    @Autowired
    private AreaGroupService areaGroupService;
    @Autowired
    private UserGroupService userGroupService;


    @Autowired
    private UserMemberService userMemberService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/userList")
    public String userList() {
        return "/users/userList";
    }

    @RequestMapping("/roleList")
    public String roleList() {
        return "/users/roleList";
    }

    @ResponseBody
    @RequestMapping("/getAllUserSimple")
    public List<SimpleUser> getAllUserSimple() {
        return userService.getAllUserSimple();
    }

    @ResponseBody
    @RequestMapping("/getRolesByUserId")
    public List<TreeInfo> getRolesByUserId(String userId) {
        List<TreeInfo> tree = new ArrayList<TreeInfo>();
        // 当前用户的最大权限
        List<RoleInfo> currentRoles = getRolesByUser(PermissionContext.getUser().getId());
        Set<String> roleIdSet = new HashSet<String>();
        Map<String, TreeInfo> map = new HashMap<String, TreeInfo>();
        if (!CollectionUtils.isEmpty(currentRoles)) {
            for (RoleInfo role : currentRoles) {
                String roleId = role.getId();
                if (!roleIdSet.contains(roleId)) {
                    roleIdSet.add(roleId);
                    TreeInfo info = new TreeInfo();
                    info.setId(roleId);
                    info.setText(role.getCname());
                    info.setChecked(false);
                    map.put(roleId, info);
                    tree.add(info);
                }


            }
        }
        // 被选中用户的权限
        List<RoleInfo> roles = getRolesByUser(userId);
        if (!CollectionUtils.isEmpty(roles)) {
            for (RoleInfo role : roles) {
                String roleId = role.getId();
                if (roleIdSet.contains(roleId)) {
                    map.get(roleId).setChecked(true);
                }
            }
        }
        return tree;
    }

    public List<RoleInfo> getRolesByUser(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            User user = userService.getById(userId);
            if (user != null) {
                List<RoleInfo> roles = userService.getRolesByUser(user);
                return roles;
            }

        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/configUserRole")
    public boolean configUserRole(String userId, String roleIds) {
        String[] roleArray = StringUtils.split(roleIds, ",");
        if (StringUtils.isNoneBlank(userId) && roleArray != null && roleArray.length > 0) {
            userRoleInfoService.saveOrUpdate(userId, roleArray);
        }
        return true;

    }

    @ResponseBody
    @RequestMapping("/getUsersByParams")
    public PageInfo<UserWebVO> getUsersByParams(int page, int rows, String username) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", username);
            PageInfo<User> results = userService.getUsersByParams(page, rows, map);
            PageInfo<UserWebVO> pageInfo = new PageInfo<UserWebVO>();
            pageInfo.setPage(results.getPage());
            pageInfo.setTotal(results.getTotal());
            List<UserWebVO> vos = new ArrayList<UserWebVO>();

            List<User> list = results.getRows();
            if (!CollectionUtils.isEmpty(list)) {
                for (User u : list) {
                    UserWebVO vo = convertToVO(u);
                    vos.add(vo);
                }
            }

            pageInfo.setRows(vos);

            return pageInfo;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private UserWebVO convertToVO(User user) {
        UserWebVO vo = new UserWebVO();
        vo.setId(user.getId());
        vo.setPhone(user.getPhone());
        vo.setUsername(user.getUsername());
        vo.setSource(user.getSource());
        vo.setOutNick(user.getOutNick());
        vo.setAdmin(user.isAdmin());
        vo.setCreatedAt(user.getCreatedAt());
        vo.setUpdateAt(user.getUpdateAt());
        vo.setWeixinNo(user.getWeixinNo());
        UserDetailInfo detailInfo = userDetailInfoService.getByUserId(vo.getId());
        if (detailInfo != null) {
            String cityCode = detailInfo.getCityCode();
            if (StringUtils.isNoneBlank(cityCode)) {
                City city = areaInfoService.getByCityCode(cityCode);
                vo.setCityCode(cityCode);
                vo.setCityName(city.getCname());
            } else {
                vo.setCityCode("");
                vo.setCityName(detailInfo.getCity() + "(微信)");
            }
            String provinceCode = detailInfo.getProvinceCode();
            if (StringUtils.isNoneBlank(provinceCode)) {
                Province province = areaInfoService.getProvinceByprovinceCode(provinceCode);
                vo.setProvinceCode(provinceCode);
                vo.setProvinceName(province.getCname());
            } else {
                vo.setProvinceCode("");
                vo.setProvinceName(detailInfo.getProvince() + "(微信)");
            }
        }
        // 从用户成员表中构建群信息
        List<UserMember> userMembers = userMemberService.getByUserId(user.getId());
        if (!CollectionUtils.isEmpty(userMembers)) {
            List<String> groupIdList = new ArrayList<String>();
            List<String> groupNameList = new ArrayList<String>();
            for (UserMember member : userMembers) {
                String groupId = member.getGroupId();
                groupIdList.add(groupId);
                UserGroup group = userGroupService.getById(groupId);
                groupNameList.add(group.getCname() + ";用户身份：" + member.getType());
            }
            vo.setGroupIds(StringUtils.join(groupIdList, ","));
            vo.setMemberInfos(StringUtils.join(groupNameList, ","));
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping("/getCurrUserRoles")
    public PageInfo<RoleInfo> getCurrentUserRoles() {
        List<RoleInfo> list = getRolesByUser(PermissionContext.getUser().getId());
        PageInfo<RoleInfo> pageInfo = new PageInfo<RoleInfo>();
        pageInfo.setTotal(list.size());
        pageInfo.setRows(list);
        return pageInfo;
    }

    @ResponseBody
    @RequestMapping("/getRoleMenus")
    public List<TreeInfo> getRoleMenus(String roleId) {
        List<TreeInfo> treeInfo = getCurrentUserMenus();
        // 遍历使得对应角色的菜单被选中

        List<RoleMenuInfo> rmInfos = roleMenuInfoService.getByRoleId(roleId);
        List<String> menuIds = new ArrayList<String>();
        for (RoleMenuInfo info : rmInfos) {
            menuIds.add(info.getMenuId());
        }
        setMenuChecked(treeInfo, menuIds);
        return treeInfo;
    }

    private void setMenuChecked(List<TreeInfo> treeInfo, List<String> menuIds) {
        if (!CollectionUtils.isEmpty(treeInfo)) {
            // 一层一层循环
            for (TreeInfo info : treeInfo) {
                if (menuIds.contains(info.getId())) {
                    info.setChecked(true);
                }
                List<TreeInfo> children = info.getChildren();
                if (!CollectionUtils.isEmpty(children)) {
                    setMenuChecked(children, menuIds);
                }

            }
        }
    }

    @ResponseBody
    @RequestMapping("/getCurrUserMenus")
    public List<TreeInfo> getCurrentUserMenus() {
        User user = PermissionContext.getUser();
        if (user.isAdmin()) {
            List<TreeInfo> tree = new ArrayList<TreeInfo>();
            List<MenuInfo> firstLevel = menuService.getMenuByParentId(IdTypeHandler.encode(1));
            for (MenuInfo menu : firstLevel) {
                TreeInfo info = new TreeInfo();
                info.setId(menu.getId());
                info.setText(menu.getCname());
                info.setState("open");
                List<MenuInfo> secondLevel = menuService.getMenuByParentId(menu.getId());
                if (!CollectionUtils.isEmpty(secondLevel)) {
                    List<TreeInfo> children = new ArrayList<TreeInfo>();
                    for (MenuInfo smenu : secondLevel) {
                        TreeInfo child = new TreeInfo();
                        child.setId(smenu.getId());
                        child.setText(smenu.getCname());
                        children.add(child);
                    }
                    info.setChildren(children);
                }
                tree.add(info);
            }
            List<TreeInfo> sysTree = new ArrayList<TreeInfo>();
            TreeInfo root = new TreeInfo();
            root.setId(IdTypeHandler.encode(0));
            root.setState("open");
            root.setText("系统菜单");
            root.setChildren(tree);
            sysTree.add(root);
            return sysTree;
        } else {
            List<TreeInfo> tree = new ArrayList<TreeInfo>();
            // 获取当前用户的第一级菜单
            List<MenuInfo> firstLevel =
                    menuService.getMenusByPIdAndUserId(IdTypeHandler.encode(1), user.getId());
            for (MenuInfo menu : firstLevel) {
                TreeInfo info = new TreeInfo();
                info.setId(menu.getId());
                info.setText(menu.getCname());
                info.setState("open");
                List<MenuInfo> secondLevel =
                        menuService.getMenusByPIdAndUserId(menu.getId(), user.getId());
                if (!CollectionUtils.isEmpty(secondLevel)) {
                    List<TreeInfo> children = new ArrayList<TreeInfo>();
                    for (MenuInfo smenu : secondLevel) {
                        TreeInfo child = new TreeInfo();
                        child.setId(smenu.getId());
                        child.setText(smenu.getCname());
                        children.add(child);
                    }
                    info.setChildren(children);
                }
                tree.add(info);
            }
            List<TreeInfo> sysTree = new ArrayList<TreeInfo>();
            TreeInfo root = new TreeInfo();
            root.setId(IdTypeHandler.encode(0));
            root.setState("open");
            root.setText("系统菜单");
            root.setChildren(tree);
            sysTree.add(root);
            return sysTree;
        }
    }

    /**
     * 前台传递的时候子选项没有选择完全，父节点是未被选中的状态，在业务层需要找到对应的parentId
     * 
     * @param roleId
     * @param menuIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/configRoleMenu")
    public boolean configRoleMenu(String roleId, String menuIds) {
        String[] menuArray = StringUtils.split(menuIds, ",");
        if (StringUtils.isNoneBlank(roleId) && menuArray != null && menuArray.length > 0) {
            roleMenuInfoService.saveOrUpdate(roleId, menuArray);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/setUserAdmin")
    public boolean setUserAdmin(String userId, boolean flag) {
        return userService.setUserAdmin(userId, flag);
    }

    @Deprecated
    @ResponseBody
    @RequestMapping("/configUserMaster")
    public boolean configUserMaster(String userId, String cname, String profile) {

        // 判断是否有群，没有群的话，建立新的群
        // step 1:用户所在的地区
        UserDetailInfo detailInfo = userDetailInfoService.getByUserId(userId);
        if (detailInfo != null) {
            String provinceCode = detailInfo.getProvinceCode();
            String cityCode = detailInfo.getCityCode();
            if (StringUtils.isNoneBlank(provinceCode) && StringUtils.isNoneBlank(cityCode)) {
                // step2 :根据地区编码获取群信息
                // 基础编码
                City city = areaInfoService.getByCityCode(cityCode);
                AreaGroupInfo agInfo =
                        areaGroupService.getByAreaIdAndType(AreaType.city, city.getId());
                String baseCode = agInfo.getCode();
                String base = baseCode.substring(0, 2);
                // 群编码匹配
                List<String> codeList = userGroupService.getCodeListByBase(base);
                String newCode = "";
                if (CollectionUtils.isEmpty(codeList)) {
                    // 建立第一个普通群
                    newCode = base + "01";
                } else {
                    // 按照顺序建立普通群
                    int maxValue = 0;
                    String suffix = "";
                    for (int i = 0; i < codeList.size(); i++) {
                        String code = codeList.get(i);
                        int value = code.charAt(3);
                        // maxValue = maxValue < value ? value : maxValue;
                        if (maxValue < value) {
                            maxValue = value;
                            suffix = code.substring(3);
                        }

                    }
                    for (int k = 0; k < GlobalValue.sequence.length; k++) {

                        if (GlobalValue.sequence[k].equals(suffix)) {
                            newCode = base + "0" + GlobalValue.sequence[k + 1];
                            break;
                        }
                    }

                }
                //
                if (StringUtils.isNoneBlank(newCode)) {
                    // 建群
                    UserGroup userGroup = new UserGroup();
                    userGroup.setCode(newCode);
                    userGroup.setCname(cname);
                    userGroup.setProfile(profile);
                    userGroup.setType(GroupType.system);
                    userGroup.setMasterId(userId);
                    userGroupService.insert(userGroup);
                    // 群成员
                    UserMember userMember = new UserMember();
                    userMember.setCode(newCode + "001");
                    userMember.setGroupId(userGroup.getId());
                    userMember.setType(MemberType.master);
                    userMember.setUserId(userId);
                    userMemberService.insert(userMember);
                }
            }

        }



        return true;
    }

    @ResponseBody
    @RequestMapping("/configUserMember")
    public boolean configUserMember(String userId, String type, String groupId) {
        return userGroupService.configUserMember(userId, MemberType.getByName(type), groupId);
    }

    /**
     * 测试方法
     * 
     * @param userId
     * @return
     */
    @Deprecated
    @ResponseBody
    @RequestMapping("/configUserGroup")
    public boolean configUserGroup(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            UserDetailInfo detailInfo = userDetailInfoService.getByUserId(userId);
            if (detailInfo != null) {
                String cityCode = detailInfo.getCityCode();
                if (StringUtils.isNoneBlank(cityCode)) {
                    City city = areaInfoService.getByCityCode(cityCode);
                    // TODO
                    AreaGroupInfo agInfo =
                            areaGroupService.getByAreaIdAndType(AreaType.city, city.getId());
                    String groupCode = agInfo.getCode();
                    //
                    UserGroup ug = userGroupService.getByGroupCode(groupCode);
                    if (ug == null) {
                        ug = new UserGroup();
                        ug.setCname(groupCode);
                        ug.setCode(groupCode);
                        ug.setImg("");
                        ug.setProfile("简介。。。。。");
                        userGroupService.insert(ug);
                    }
                    // 查询用户成员里的最大编码
                    UserMember member = new UserMember();
                    member.setGroupId(ug.getId());
                    member.setUserId(userId);
                    member.setType(MemberType.member);
                    //
                    String maxCode = userMemberService.getMaxCodeByGroupId(member.getGroupId());
                    if (StringUtils.isBlank(maxCode)) {
                        maxCode = groupCode + "000";
                    }
                    long currentCode = Long.parseLong(maxCode) + 1;
                    member.setCode(currentCode + "");
                    userMemberService.insert(member);
                }
            }
        }


        return true;
    }

    @ResponseBody
    @RequestMapping("/addRole")
    public boolean addRole(String cname) {
        RoleInfo info = new RoleInfo();
        info.setCname(cname);
        info.setMemo("角色：" + cname);
        roleService.insert(info);
        return true;
    }

}
