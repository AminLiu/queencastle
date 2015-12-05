package com.queencastle.web.controllers.relations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.model.UserDetailInfo;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.model.relations.AuditStatus;
import com.queencastle.dao.model.relations.UserAudit;
import com.queencastle.dao.model.relations.UserManager;
import com.queencastle.dao.model.relations.UserRelation;
import com.queencastle.dao.utils.DateUtils;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.relations.UserAuditService;
import com.queencastle.service.interf.relations.UserGroupService;
import com.queencastle.service.interf.relations.UserManagerService;
import com.queencastle.service.interf.relations.UserRelationService;
import com.queencastle.service.interf.weixin.WeiXinService;
import com.queencastle.service.sessions.PermissionContext;
import com.queencastle.service.utils.KFAccountHelper;
import com.queencastle.service.utils.WeiXinHelper;
import com.queencastle.web.vo.UserAuditVO;

@Controller
@RequestMapping("/userAudit")
public class UserAuditController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserAuditService userAuditService;
    @Autowired
    private UserService userService;

    @Autowired
    private WeiXinService weiXinService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private UserDetailInfoService userDetailInfoService;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private UserManagerService userManagerService;

    @RequestMapping("/index")
    public String index() {
        return "/relations/userAuditList";
    }


    // 审核工作交给省级管理员做
    @Transactional
    @ResponseBody
    @RequestMapping("/audit")
    public boolean audit(String id, String result, String reason) {
        User user = PermissionContext.getUser();
        UserAudit userAudit = new UserAudit();
        userAudit.setId(id);
        userAudit.setAuditUser(user);
        AuditStatus status = AuditStatus.getByName(result);
        userAudit.setAuditStatus(status);
        userAudit.setReason(reason);
        userAuditService.updateStatusAndReason(userAudit);
        // 发送通知消息
        UserAudit audit = userAuditService.getById(id);
        String applyUserId = audit.getApplyUser().getId();
        User applyUser = userService.getById(applyUserId);
        String openId = applyUser.getOpenId();
        // 构建图文消息发送给用户
        String accessToken = weiXinService.getAccessToken();
        // 构建完整消息发送给用户
        List<String> msgList = new ArrayList<String>();
        msgList.add("哈喽！恭喜您已经通过系统审核，系统正在联系管理员，为您做入群准备，请耐心等候\n");
        KFAccountHelper.sendMsgByKF(openId, StringUtils.join(msgList, " "), accessToken,
                restTemplate);
        // //////////////////////////////申请用户的地理信息
        UserDetailInfo ud = userDetailInfoService.getByUserId(applyUserId);
        String pcode = ud.getProvinceCode();
        Province pc = areaInfoService.getProvinceByprovinceCode(pcode);
        String ccode = ud.getCityCode();
        City cc = areaInfoService.getByCityCode(ccode);

        // 查找到对应的省级管理员，同意并指定地区管理员去拉群
        UserDetailInfo detailInfo = userDetailInfoService.getByUserId(applyUserId);
        String provinceCode = detailInfo.getProvinceCode();
        Province province = areaInfoService.getProvinceByprovinceCode(provinceCode);
        // 查找到省份的管理员
        UserManager userManager = userManagerService.getProvinceManagerByAreaId(province.getId());
        if (userManager != null) {
            // 省级管理员的编号
            String managerUserId = userManager.getUserId();
            User manager = userService.getById(managerUserId);
            if (manager != null) {
                // 发送通过链接,需要发送模板消息
                String msg = "地址:" + pc.getCname() + cc.getCname();

                String paramUrl =
                        "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5d969d791a2f8855&redirect_uri=http%3a%2f%2fwww.queencastle.cn%2fweixinApi%2fprovinceAudit%3FuserId%3D"
                                + applyUserId
                                + "&response_type=code&scope=snsapi_base&state=bdx#wechat_redirect";
                // for test
                // WeiXinHelper.templateProxyMessage(restTemplate, "o0EQqtztBibckN5O-Lz1ivxkok8k",
                // paramUrl, applyUser, accessToken, msg);
                WeiXinHelper.templateProxyMessage(restTemplate, manager.getOpenId(), paramUrl,
                        applyUser, accessToken, msg);
            }
        }

        return false;
    }

    @Deprecated
    @Transactional
    @ResponseBody
    @RequestMapping("/auditBefore")
    public boolean auditBefore(String id, String result, String reason) {
        User user = PermissionContext.getUser();
        UserAudit userAudit = new UserAudit();
        userAudit.setId(id);
        userAudit.setAuditUser(user);
        AuditStatus status = AuditStatus.getByName(result);
        userAudit.setAuditStatus(status);
        userAudit.setReason(reason);
        userAuditService.updateStatusAndReason(userAudit);
        // 发送通知消息
        UserAudit audit = userAuditService.getById(id);
        String applyUserId = audit.getApplyUser().getId();
        User applyUser = userService.getById(applyUserId);
        String openId = applyUser.getOpenId();
        // 构建图文消息发送给用户
        String accessToken = weiXinService.getAccessToken();
        // 构建完整消息发送给用户
        List<String> msgList = new ArrayList<String>();
        msgList.add("哈喽！恭喜您成为女王城堡的子爵\n");
        msgList.add("您的会员编号：" + applyUser.getOutNick() + "\n");
        msgList.add("您的加入时间：" + DateUtils.getCurrentYmd() + "\n");

        msgList.add("您已经是女王城堡的一员啦！快来寻找一起并肩作战的闺蜜和风雨同舟的家族吧！\n");
        msgList.add("------------------------\n");
        msgList.add("寻找家族请点击<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5d969d791a2f8855&redirect_uri=http%3a%2f%2fwww.queencastle.cn%2fweixinApi%2fbecomeMember&response_type=code&scope=snsapi_base&state=bdx#wechat_redirect\">查看详情</a>");
        KFAccountHelper.sendMsgByKF(openId, StringUtils.join(msgList, " "), accessToken,
                restTemplate);
        // 查看这个人是否有推荐的上级,进行群的分配
        UserRelation userRelation = userRelationService.getByUserId(applyUserId);
        if (userRelation != null) {
            userGroupService.handleSelfBuildGroup(userRelation.getParentId(), applyUserId);


        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/getUserAuditsByParam")
    public PageInfo<UserAuditVO> getUserAuditsByParam(int page, int rows) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            PageInfo<UserAudit> results = userAuditService.getByParams(page, rows, map);
            PageInfo<UserAuditVO> pageInfo = new PageInfo<UserAuditVO>();
            pageInfo.setPage(results.getPage());
            pageInfo.setTotal(results.getTotal());
            List<UserAuditVO> vos = new ArrayList<UserAuditVO>();
            List<UserAudit> list = results.getRows();
            if (!CollectionUtils.isEmpty(list)) {
                for (UserAudit userAudit : list) {
                    String applyUserId = userAudit.getApplyUser().getId();
                    UserAuditVO vo = new UserAuditVO();
                    vo.setId(userAudit.getId());
                    User applyUser = userService.getById(applyUserId);
                    vo.setApplyUserId(applyUserId);
                    vo.setApplyUserName(applyUser.getUsername());
                    User auditUser = userAudit.getAuditUser();
                    if (auditUser == null || StringUtils.isBlank(auditUser.getId())) {
                        vo.setAuditUserName("");
                    } else {
                        try {
                            auditUser = userService.getById(userAudit.getAuditUser().getId());
                            vo.setAuditUserName(auditUser.getUsername());
                        } catch (Exception e) {
                            logger.error("====" + auditUser + "----");
                        }
                    }
                    vo.setAuditStatus(userAudit.getAuditStatus());
                    vo.setReason(userAudit.getReason());
                    vo.setCreatedAt(userAudit.getCreatedAt());
                    vos.add(vo);
                }
            }
            pageInfo.setRows(vos);
            return pageInfo;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
