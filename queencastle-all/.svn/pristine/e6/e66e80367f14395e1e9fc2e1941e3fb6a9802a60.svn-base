package com.queencastle.web.controllers.relations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.SysResourceInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.model.relations.AreaGroupInfo;
import com.queencastle.dao.model.relations.AreaType;
import com.queencastle.dao.model.relations.GroupLevel;
import com.queencastle.dao.model.relations.GroupType;
import com.queencastle.dao.model.relations.UserGroup;
import com.queencastle.dao.model.relations.UserMember;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.dao.utils.DateUtils;
import com.queencastle.dao.utils.MD5Util;
import com.queencastle.dao.vo.SimpleUserGroupVO;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.interf.ResourceUploadService;
import com.queencastle.service.interf.SysResourceInfoService;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.relations.AreaGroupService;
import com.queencastle.service.interf.relations.UserGroupService;
import com.queencastle.service.interf.relations.UserMemberService;
import com.queencastle.service.interf.weixin.WeiXinService;
import com.queencastle.service.sessions.PermissionContext;
import com.queencastle.web.controllers.goods.ProductCategoryController;

@Controller
@RequestMapping("/relations")
public class UserGroupMemberController {

    private static Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private UserMemberService userMemberService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceUploadService resourceUploadService;
    @Autowired
    private SysResourceInfoService sysResourceInfoService;
    @Autowired
    private AreaInfoService areaInfoService;

    @Autowired
    private WeiXinService weiXinService;
    @Autowired
    private AreaGroupService areaGroupService;
    @Autowired
    private UserDetailInfoService userDetailInfoService;


    @RequestMapping("/userGroupList")
    public String userGroupList(Model model, String id) {
        /* String id=IdTypeHandler.encode(33L); */

        if (id != null) {
            UserGroup userGroup = userGroupService.getById(id);
            model.addAttribute("userGroup", userGroup);
            return "/relations/userGroupList";
        } else {
            String id2 = IdTypeHandler.encode(1L);
            UserGroup userGroup = userGroupService.getById(id2);
            model.addAttribute("userGroup", userGroup);
            return "/relations/userGroupList";
        }

    }

    @RequestMapping("/userGroupLevelList")
    public String userGroupLevelList() {
        return "/relations/userGLevelList";
    }

    @ResponseBody
    @RequestMapping("/getUGroupLevelsByParams")
    public PageInfo<UserGroup> getUGroupLevelsByParams(int page, int rows, String glevel) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glevel", glevel);
        PageInfo<UserGroup> pageInfo = userGroupService.getUGroupLevelsByParams(page, rows, map);
        List<UserGroup> list = pageInfo.getRows();
        if (list.size() > 0) {
            for (UserGroup ug : list) {
                String img = ug.getImg();
                if (StringUtils.isNoneBlank(img)) {
                    img = GlobalValue.QINIU_HOST + img;
                    ug.setImg(img);
                }
            }
        }
        return pageInfo;
    }

    @RequestMapping("/userMemberList")
    public String userMemberList() {
        return "/relations/userMemberList";
    }

    @RequestMapping("/myGroupList")
    public String myGroupList() {
        return "/relations/myGroupList";
    }

    @RequestMapping("/myMemberList")
    public String myMemberList() {
        return "/relations/myMemberList";
    }

    @ResponseBody
    @RequestMapping("/getUserGroupById")
    public UserGroup getUserGroupById(String groupId) {
        if (StringUtils.isNoneBlank(groupId)) {
            UserGroup userGroup = userGroupService.getById(groupId);
            String img = userGroup.getImg();
            if (StringUtils.isNoneBlank(img)) {
                img = GlobalValue.QINIU_HOST + img;
                userGroup.setImg(img);
            }
            return userGroup;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/getMyUserGroupsByParams")
    public PageInfo<UserGroup> getMyUserGroupsByParams(int page, int rows) {
        // 当前用户为群主的群
        User user = PermissionContext.getUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getId());
        PageInfo<UserGroup> pageInfo = userGroupService.getUserGroupsByParams(page, rows, map);
        List<UserGroup> list = pageInfo.getRows();
        if (list.size() > 0) {
            for (UserGroup ug : list) {
                String img = ug.getImg();
                if (StringUtils.isNoneBlank(img)) {
                    img = GlobalValue.QINIU_HOST + img;
                    ug.setImg(img);
                }
            }
        }

        return pageInfo;

    }


    @ResponseBody
    @RequestMapping("/getUserGroupsByParams")
    public PageInfo<UserGroup> getUserGroupsByParams(int page, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<UserGroup> pageInfo = userGroupService.getUserGroupsByParams(page, rows, map);

        List<UserGroup> list = pageInfo.getRows();
        if (list.size() > 0) {
            for (UserGroup ug : list) {
                String img = ug.getImg();
                if (StringUtils.isNoneBlank(img)) {
                    img = GlobalValue.QINIU_HOST + img;
                    ug.setImg(img);
                }
            }
        }

        return pageInfo;
    }

    @ResponseBody
    @RequestMapping("/getMyMembersByParams")
    public PageInfo<UserMemberVO> getMyMembersByParams(int page, int rows, String userCode) {
        User user = PermissionContext.getUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userCode", userCode);
        map.put("userId", user.getId());
        PageInfo<UserMember> results = userMemberService.getMyMembersByParams(page, rows, map);
        PageInfo<UserMemberVO> pageInfo = new PageInfo<UserMemberVO>();
        pageInfo.setPage(results.getPage());
        pageInfo.setTotal(results.getTotal());
        List<UserMember> list = results.getRows();
        List<UserMemberVO> vos = new ArrayList<UserMemberVO>();
        if (!CollectionUtils.isEmpty(list)) {
            for (UserMember member : list) {
                convetMemberVO(vos, member);
            }
        }
        pageInfo.setRows(vos);

        return pageInfo;

    }



    @ResponseBody
    @RequestMapping("/getUserMemberssByParams")
    public PageInfo<UserMemberVO> getUserMemberssByParams(int page, int rows, String userCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userCode", userCode);
        PageInfo<UserMember> results = userMemberService.getByParams(page, rows, map);

        PageInfo<UserMemberVO> pageInfo = new PageInfo<UserMemberVO>();
        pageInfo.setPage(results.getPage());
        pageInfo.setTotal(results.getTotal());
        List<UserMember> list = results.getRows();

        List<UserMemberVO> vos = new ArrayList<UserMemberVO>();
        if (!CollectionUtils.isEmpty(list)) {

            for (UserMember member : list) {
                convetMemberVO(vos, member);
            }

        }
        pageInfo.setRows(vos);

        return pageInfo;
    }

    private void convetMemberVO(List<UserMemberVO> vos, UserMember member) {
        UserMemberVO vo = new UserMemberVO();
        vo.setId(member.getId());
        String userId = member.getUserId();
        vo.setUserId(userId);
        User user = userService.getById(userId);
        if (user == null) {
            user = new User();
            user.setAdmin(false);
            user.setPhone(RandomStringUtils.randomNumeric(11));
            user.setUsername(RandomStringUtils.randomAlphanumeric(10));
            String password = "123456";
            password = MD5Util.MD5Encode(password);
            user.setPassword(password);
            user.setSource("fake");
            user.setOutNick(RandomStringUtils.randomAlphanumeric(10));
            userService.insert(user);
        }
        vo.setUsername(user.getUsername());
        vo.setPhone(user.getPhone());
        vo.setCode(member.getCode());
        vo.setType(member.getType());
        vo.setCreatedAt(member.getCreatedAt());
        String groupId = member.getGroupId();
        vo.setGroupId(groupId);
        UserGroup group = userGroupService.getById(groupId);
        if (group == null) {
            group = new UserGroup();
            group.setCname("浙江省超级VIP群");
            group.setCode("8001999");
            userGroupService.insert(group);
        }
        vo.setGroupName(group.getCname());
        vos.add(vo);
    }

    @ResponseBody
    @RequestMapping("/editUserGroup")
    public boolean editUserGroup(String id, String cname, String profile, @RequestParam(
            value = "img", required = false) MultipartFile imgFile) {
        String img = saveGroupImg(imgFile);
        UserGroup ug = new UserGroup();
        ug.setId(id);
        ug.setCname(cname);
        ug.setProfile(profile);
        ug.setImg(img);
        userGroupService.updateById(ug);
        return true;
    }

    @ResponseBody
    @RequestMapping("/saveUserGroup")
    public boolean saveUserGroup(String cname, String profile, @RequestParam(value = "img",
            required = false) MultipartFile imgFile) {
        String img = saveGroupImg(imgFile);
        User user = PermissionContext.getUser();
        return userGroupService.selfBuildGroup(cname, img, profile, user);
    }

    private String saveGroupImg(MultipartFile imgFile) {
        User currentUser = PermissionContext.getUser();
        byte[] bytes;
        try {
            bytes = imgFile.getBytes();
            if (bytes.length > 0) {
                String originName = imgFile.getOriginalFilename();
                String fileName = DateUtils.getCurrFullTime() + "_" + originName;
                String key = resourceUploadService.uploadBytes(bytes, fileName);
                if (StringUtils.isNoneBlank(key)) {
                    SysResourceInfo info = new SysResourceInfo();
                    info.setFileKey(key);
                    info.setFileName(fileName);
                    info.setOriginName(originName);
                    String ext = originName.substring((originName.lastIndexOf(".") + 1));
                    info.setFileExt(ext);
                    if (currentUser != null) {
                        info.setUserId(currentUser.getId());
                    } else {
                        info.setUserId(IdTypeHandler.encode(1L));
                    }
                    sysResourceInfoService.insert(info);
                    return fileName;
                }

            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    /************************** 微信群管理 **************************************/

    @RequestMapping("/syncUsers")
    @ResponseBody
    public int syncUsers() {
        weiXinService.syncUserWithOnLine();
        return 0;
    }

    @RequestMapping("/initCityAreaCode")
    @ResponseBody
    public int initCityAreaCode() {
        List<Province> provinces = areaInfoService.getAllProvinces();
        for (Province province : provinces) {
            String provinceCode = province.getCode();
            List<City> citys = areaInfoService.getByProvinceCode(provinceCode);
            AreaGroupInfo ag =
                    areaGroupService.getByAreaIdAndType(AreaType.province, province.getId());
            String baseCode = ag.getCode();
            for (int i = 0; i < citys.size(); i++) {
                City city = citys.get(i);
                AreaGroupInfo areaGroup = new AreaGroupInfo();
                areaGroup.setAreaId(city.getId());
                areaGroup.setType(AreaType.city);
                String code = baseCode + GlobalValue.sequence[i] + "00";
                areaGroup.setCode(code);
                areaGroupService.insert(areaGroup);
            }
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/getAllUsableGroup")
    public List<UserGroup> getAllUsableGroup() {
        return userGroupService.getAllUsableGroup();
    }

    @ResponseBody
    @RequestMapping("/buildAreaUserGroup")
    public boolean buildAreaUserGroup(String areaId, String cname, String areaPrefix,
            String areaSuffix, String profile,
            @RequestParam(value = "img", required = false) MultipartFile imgFile) {
        String img = saveGroupImg(imgFile);
        UserGroup userGroup = new UserGroup();
        userGroup.setImg(img);
        userGroup.setCode(areaPrefix + areaSuffix);
        userGroup.setProfile(profile);
        // 如果是00结尾，说明是市级群
        if (areaPrefix.endsWith("00")) {
            userGroup.setGlevel(GroupLevel.province);
        } else {
            userGroup.setGlevel(GroupLevel.city);
        }
        userGroup.setMasterId(null);
        userGroup.setType(GroupType.system);
        userGroup.setCname(cname);
        userGroup.setAreaId(areaId);
        userGroupService.insert(userGroup);
        return true;
    }

    @ResponseBody
    @RequestMapping("/editAreaUserGroup")
    public boolean editAreaUserGroup(String areaId, String id, String cname, String areaPrefix,
            String areaSuffix, String profile,
            @RequestParam(value = "img", required = false) MultipartFile imgFile) {
        String img = saveGroupImg(imgFile);
        UserGroup userGroup = new UserGroup();
        userGroup.setId(id);
        userGroup.setImg(img);
        userGroup.setCode(areaPrefix + areaSuffix);
        userGroup.setProfile(profile);
        if (areaPrefix.endsWith("00")) {
            userGroup.setGlevel(GroupLevel.province);
        } else {
            userGroup.setGlevel(GroupLevel.city);
        }
        userGroup.setMasterId(null);
        userGroup.setType(GroupType.system);
        userGroup.setCname(cname);
        userGroup.setAreaId(areaId);
        userGroupService.updateById(userGroup);
        return true;
    }

    @ResponseBody
    @RequestMapping("/getCityGroups")
    public List<SimpleUserGroupVO> getCityGroups(String areaId) {
        // List<UserGroup> list = userGroupService.getAllUsableByAreaId(areaId, GroupLevel.city);
        List<UserGroup> list = userGroupService.getCityGroupsByCityId(areaId);
        List<SimpleUserGroupVO> results = new ArrayList<SimpleUserGroupVO>();
        if (!CollectionUtils.isEmpty(list)) {
            for (UserGroup ug : list) {
                // 判断是否满员
                String groupId = ug.getId();
                int number = userMemberService.getMemberCntById(groupId);
                if (number < GlobalValue.GROUP_SIZE) {
                    SimpleUserGroupVO vo = new SimpleUserGroupVO();
                    vo.setId(ug.getId());
                    vo.setCname(ug.getCname());
                    results.add(vo);
                }
            }
        }
        return results;
    }



}
