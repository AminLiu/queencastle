package com.queencastle.service.impl.relations;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.relations.UserGroupMapper;
import com.queencastle.dao.mapper.relations.UserMemberMapper;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.UserDetailInfo;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.loggs.LogType;
import com.queencastle.dao.model.loggs.UserLog;
import com.queencastle.dao.model.relations.AreaGroupInfo;
import com.queencastle.dao.model.relations.AreaType;
import com.queencastle.dao.model.relations.GroupType;
import com.queencastle.dao.model.relations.MemberType;
import com.queencastle.dao.model.relations.UserGroup;
import com.queencastle.dao.model.relations.UserMember;
import com.queencastle.dao.vo.CountVO;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.loggs.UserLogService;
import com.queencastle.service.interf.relations.AreaGroupService;
import com.queencastle.service.interf.relations.UserGroupService;
import com.queencastle.service.interf.relations.UserMemberService;
import com.queencastle.service.interf.weixin.WeiXinService;
import com.queencastle.service.utils.KFAccountHelper;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private UserMemberMapper userMemberMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private UserDetailInfoService userDetailInfoService;
    @Autowired
    private UserMemberService userMemberService;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private AreaGroupService areaGroupService;
    @Autowired
    private WeiXinService weiXinService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public int insert(UserGroup userGroup) {
        return userGroupMapper.insert(userGroup);
    }

    @Override
    public UserGroup getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return userGroupMapper.getById(id);
        }
        return null;
    }

    @Override
    public List<UserGroup> getByCode(String code) {
        if (StringUtils.isNoneBlank(code)) {
            return userGroupMapper.getByCode(code);
        }
        return null;
    }

    @Override
    public PageInfo<UserGroup> getUserGroupsByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<UserGroup> pageInfo = new PageInfo<UserGroup>();
        pageInfo.setPage(page);
        Integer count = userGroupMapper.getUserGroupsCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<UserGroup>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<UserGroup> list = userGroupMapper.getUserGroupsByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public UserGroup getByGroupCode(String groupCode) {
        if (StringUtils.isNoneBlank(groupCode)) {
            return userGroupMapper.getByGroupCode(groupCode);
        }
        return null;
    }

    @Override
    public boolean updateById(UserGroup userGroup) {

        return userGroupMapper.updataById(userGroup);
    }

    @Override
    public List<String> getCodeListByBase(String base) {
        return userGroupMapper.getCodeListByBase(base);
    }

    @Override
    public List<UserGroup> getAllUsableGroup() {
        List<CountVO> groupIdList = userMemberMapper.getAllUsableGroupId(GlobalValue.GROUP_SIZE);
        List<UserGroup> list = new ArrayList<UserGroup>();
        if (!CollectionUtils.isEmpty(groupIdList)) {
            for (CountVO vo : groupIdList) {
                UserGroup ug = getById(vo.getId());
                if (ug != null) {
                    list.add(ug);
                }
            }
        }
        return list;
    }

    @Override
    public List<UserGroup> getByMasterId(String masterId, GroupType type) {
        if (StringUtils.isNoneBlank(masterId)) {
            return userGroupMapper.getByMasterId(masterId, type);
        }

        return null;
    }

    @Override
    public boolean handleSelfBuildGroup(String parentId, String userId) {
        User user = userService.getById(userId);
        User parent = userService.getById(parentId);
        String accessToken = weiXinService.getAccessToken();
        // 看是否是自建群，两个条件 推荐人是群主，群类型为自建群
        // 如果只有一个群，自动归类到这个群，如果多个暂不处理
        List<UserGroup> groups = getByMasterId(parentId, GroupType.individual);
        logger.error("groups:" + groups);
        if (groups != null && groups.size() == 1) {
            String groupId = groups.get(0).getId();

            // 判断是否已经加入对应的群
            UserMember tmp = userMemberService.getByGroupIdAndUserId(groupId, userId);
            if (tmp != null) {
                logger.error("该用户已经加入对应的群了");
                return false;
            }
            // 获取自己群最大编号
            UserMember userMember = new UserMember();
            userMember.setGroupId(groupId);
            userMember.setUserId(userId);
            userMember.setType(MemberType.member);
            String maxCode = userMemberMapper.getMaxCodeByGroupId(groupId);
            Integer seq = Integer.parseInt(maxCode.substring(4));
            seq++;
            NumberFormat format = NumberFormat.getInstance();
            format.setGroupingUsed(false);
            format.setMaximumIntegerDigits(3);
            format.setMinimumIntegerDigits(3);
            userMember.setCode(maxCode.substring(0, 4) + format.format(seq));

            userMemberMapper.insert(userMember);
            UserLog userLog = new UserLog();

            userLog.setContent("您的自建群群员【" + user.getOutNick() + "】加入本系统");
            userLog.setLogType(LogType.member);
            userLog.setObjectId(userMember.getId());
            userLog.setUserId(parentId);
            userLogService.insert(userLog);
            // ////////////////////

            String openId = parent.getOpenId();
            KFAccountHelper.sendMsgByKF(openId, "你的成员" + user.getOutNick() + "已经加入女王城堡",
                    accessToken, restTemplate);

            KFAccountHelper.sendMsgByKF(user.getOpenId(), "您已经加入" + parent.getOutNick() + "的自建群",
                    accessToken, restTemplate);

            // /////////////

            return true;
        } else if (groups == null || CollectionUtils.isEmpty(groups)) {
            // 这个人不是群主,根据这个人的地区信息进行处理
            UserDetailInfo detailInfo = userDetailInfoService.getByUserId(userId);
            if (detailInfo == null) {
                return false;
            }
            // 获取到该地区对应的可用编码
            City city = areaInfoService.getByCityCode(detailInfo.getCityCode());
            AreaGroupInfo agInfo = areaGroupService.getByAreaIdAndType(AreaType.city, city.getId());
            // 地区编码
            String baseCode = agInfo.getCode();

            String base = baseCode.substring(0, 2);
            // 群编码匹配
            List<String> codeList = getCodeListByBase(base);
            if (CollectionUtils.isEmpty(codeList)) {
                return false;
            }
            // 获取到这个群的编码
            String groupId = "";
            int maxValue = 0;
            String suffix = "";
            for (int i = 0; i < codeList.size(); i++) {
                String code = codeList.get(i);
                int value = code.charAt(3);
                if (maxValue < value) {
                    maxValue = value;
                    suffix = code.substring(3);
                }

            }
            for (int k = 0; k < GlobalValue.sequence.length; k++) {
                if (GlobalValue.sequence[k].equals(suffix)) {
                    groupId = GlobalValue.sequence[k];
                    break;
                }
            }
            if (StringUtils.isNoneBlank(groupId)) {
                configUserMember(userId, MemberType.member, groupId);
            }
        } else if (!CollectionUtils.isEmpty(groups)) {
            String openId = parent.getOpenId();
            KFAccountHelper.sendMsgByKF(openId, "你的成员" + user.getOutNick() + "已经加入女王城堡，请为其分配自建群",
                    accessToken, restTemplate);
        }

        return false;
    }

    @Override
    public boolean configUserMember(String userId, MemberType type, String groupId) {

        // 需要分配到特定的群内，人数已满的群不参与，只需获得该群的最大编码即可
        UserMember userMember = new UserMember();
        userMember.setGroupId(groupId);
        userMember.setUserId(userId);
        userMember.setType(type);
        String maxCode = userMemberService.getMaxCodeByGroupId(groupId);

        Integer seq = Integer.parseInt(maxCode.substring(4));
        seq++;
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMaximumIntegerDigits(3);
        format.setMinimumIntegerDigits(3);
        userMember.setCode(maxCode.substring(0, 4) + format.format(seq));

        userMemberService.insert(userMember);
        return true;

    }

    @Override
    public boolean selfBuildGroup(String cname, String img, String profile, User user) {

        // 设置群编码

        UserDetailInfo detailInfo = userDetailInfoService.getByUserId(user.getId());
        String cityCode = detailInfo.getCityCode();
        City city = areaInfoService.getByCityCode(cityCode);
        AreaGroupInfo agInfo = areaGroupService.getByAreaIdAndType(AreaType.city, city.getId());
        String baseCode = agInfo.getCode();
        String base = baseCode.substring(0, 2);
        // 自建群这里编码为1
        base += "1";
        List<String> codeList = getCodeListByBase(base);
        String newCode = "";
        if (CollectionUtils.isEmpty(codeList)) {
            newCode = base + "1";
        } else {
            // 按照顺序建群
            int maxValue = 0;
            String suffix = "";
            for (int i = 0; i < codeList.size(); i++) {
                String code = codeList.get(i);
                int value = code.charAt(3);
                if (maxValue < value) {
                    maxValue = value;
                    suffix = code.substring(3);
                }

            }
            for (int k = 0; k < GlobalValue.sequence.length; k++) {
                if (GlobalValue.sequence[k].equals(suffix)) {
                    newCode = base + GlobalValue.sequence[k + 1];
                    break;
                }
            }
        }
        if (StringUtils.isNoneBlank(newCode)) {
            // 建群
            UserGroup userGroup = new UserGroup();
            userGroup.setCode(newCode);
            userGroup.setCname(cname);
            userGroup.setProfile(profile);
            userGroup.setType(GroupType.individual);
            userGroup.setImg(img);
            userGroup.setMasterId(user.getId());
            insert(userGroup);
            // 群成员
            UserMember userMember = new UserMember();
            userMember.setCode(newCode + "001");
            userMember.setGroupId(userGroup.getId());
            userMember.setType(MemberType.master);
            userMember.setUserId(user.getId());
            userMemberService.insert(userMember);
        }
        return true;
    }

}
