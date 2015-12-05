package com.queencastle.web.controllers.relations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.area.Area;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.model.relations.GroupLevel;
import com.queencastle.dao.model.relations.MemberType;
import com.queencastle.dao.model.relations.UserGroup;
import com.queencastle.dao.model.relations.UserManager;
import com.queencastle.dao.model.relations.UserMember;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.dao.vo.SimpleUser;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.relations.UserGroupService;
import com.queencastle.service.interf.relations.UserManagerService;
import com.queencastle.service.interf.relations.UserMemberService;
import com.queencastle.web.vo.ManagerVO;

@Controller
@RequestMapping("/userAdmin")
public class UserManagerController {

    @Autowired
    private UserManagerService userManagerService;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private UserMemberService userMemberService;

    @RequestMapping("/index")
    public String index() {
        return "relations/userManageList";
    }

    @ResponseBody
    @RequestMapping("/getAreaUserByParam")
    public PageInfo<SimpleUser> getAreaUserByParam(int page, int rows, String level, String areaId) {

        GroupLevel groupLevel = GroupLevel.getByName(level);
        if (StringUtils.isNoneBlank(level) && StringUtils.isNoneBlank(areaId)) {

            if (groupLevel == GroupLevel.province) {
                Province province = areaInfoService.getProvinceById(areaId);
                return userService.getProvinceUserByCode(page, rows, province.getCode());
            }
            if (groupLevel == GroupLevel.city) {
                City city = areaInfoService.getCityById(areaId);
                return userService.getCityUserByCode(page, rows, city.getCode());
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/getByParams")
    public PageInfo<ManagerVO> getByParams(int page, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<UserManager> results = userManagerService.getByParams(page, rows, map);
        PageInfo<ManagerVO> pageInfo = new PageInfo<ManagerVO>();
        pageInfo.setPage(results.getPage());
        pageInfo.setTotal(results.getTotal());
        List<ManagerVO> vos = new ArrayList<ManagerVO>();
        List<UserManager> list = results.getRows();
        if (!CollectionUtils.isEmpty(list)) {
            for (UserManager manager : list) {
                vos.add(convertToVo(manager));
            }
        }

        pageInfo.setRows(vos);
        return pageInfo;
    }

    @ResponseBody
    @RequestMapping("/saveUserManager")
    public boolean saveUserManager(String userId, String level, String areaId, String cname) {
        GroupLevel groupLevel = GroupLevel.getByName(level);
        if (StringUtils.isNoneBlank(level) && StringUtils.isNoneBlank(areaId)) {
            UserManager manager = new UserManager();
            manager.setUserId(userId);
            manager.setCname(cname);
            manager.setAreaId(areaId);
            manager.setLevel(groupLevel);
            // 查找上级节点
            if (groupLevel == GroupLevel.province) {
                manager.setManagerId(IdTypeHandler.encode(0));


            }
            if (groupLevel == GroupLevel.city) {
                City city = areaInfoService.getCityById(areaId);
                String provinceCode = city.getProvinceCode();
                Province province = areaInfoService.getProvinceByprovinceCode(provinceCode);
                UserManager parent =
                        userManagerService.getProvinceManagerByAreaId(province.getId());
                if (parent != null) {
                    manager.setManagerId(parent.getId());
                } else {
                    manager.setManagerId(null);
                }

            }
            userManagerService.insert(manager);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/configMasterForUserGroup")
    public boolean configMasterForUserGroup(String groupId, String userId) {
        UserGroup userGroup = userGroupService.getById(groupId);
        if (userGroup != null && StringUtils.isBlank(userGroup.getMasterId())) {
            userGroup.setMasterId(userId);
            userGroupService.updateById(userGroup);
            // 在用户群成员列表中设置一号群主
            UserMember userMember = new UserMember();
            userMember.setGroupId(groupId);
            userMember.setUserId(userId);
            userMember.setType(MemberType.master);
            userMember.setCode(userGroup.getCode() + "001");
            // TODO:默认都是市一级的
            userMember.setAreaId(userGroup.getAreaId());
            userMemberService.insert(userMember);
        }
        return false;
    }

    private ManagerVO convertToVo(UserManager manager) {
        ManagerVO vo = new ManagerVO();
        String areaId = manager.getAreaId();
        GroupLevel level = manager.getLevel();
        String userId = manager.getUserId();

        vo.setId(manager.getId());
        vo.setAreaId(areaId);
        vo.setUserId(userId);
        String areaName = "";
        if (level == GroupLevel.province) {
            Province province = areaInfoService.getProvinceById(areaId);
            areaName = province.getCname();
        }
        if (level == GroupLevel.city) {
            City city = areaInfoService.getCityById(areaId);
            String provinceCode = city.getProvinceCode();
            Province province = areaInfoService.getProvinceByprovinceCode(provinceCode);
            areaName = province.getCname() + "--->" + city.getCname();
        }
        if (level == GroupLevel.area) {
            Area area = areaInfoService.getAreaById(areaId);
            String cityCode = area.getCityCode();
            City city = areaInfoService.getByCityCode(cityCode);
            String provinceCode = city.getProvinceCode();
            Province province = areaInfoService.getProvinceByprovinceCode(provinceCode);
            areaName = province.getCname() + "--->" + city.getCname() + "--->" + area.getCname();
        }

        vo.setAreaName(areaName);
        User user = userService.getById(userId);
        if (user != null) {
            vo.setUserName(user.getUsername());
        }
        vo.setCname(manager.getCname());
        vo.setLevel(level);
        vo.setCreatedAt(manager.getCreatedAt());
        vo.setUpdateAt(manager.getUpdateAt());
        return vo;
    }
}
