package com.queencastle.weixin.controllers.weixin;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.queencastle.dao.model.User;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.utils.jedis.StringJedisCache;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
@RequestMapping("/weixinApi")
public class WeiXinApiController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private UserDetailInfoService userDetailInfoService;
    @Autowired
    private StringJedisCache accessTokenCache;


    @RequestMapping("/queenIdx")
    public String queenIdx() {
        return "redirect:/queenIdx";
    }

    @RequestMapping("/becomeMember")
    public String becomeMember(Model model) {
        return "redirect:/becomeMember";
    }

    @RequestMapping("/demandCenter")
    public String demandCenter() {
        return "redirect:/dsManager/getInfosByType?type=demand";
    }

    @RequestMapping("/supplyCenter")
    public String supplyCenter() {
        return "redirect:/dsManager/getInfosByType?type=supply";
    }

    @RequestMapping("/userCenter")
    public String userCenter(Model model) {
        User user = PermissionContext.getUser();
        if (user != null) {
            // 判断用户名是否为空，如果为空，说明没有个人信息填写，跳转到完善信息的页面
            String username = user.getUsername();
            if (StringUtils.isBlank(username)) {
                List<Province> provinces = areaInfoService.getAllProvinces();
                model.addAttribute("provinces", provinces);
                return "/makeup";
            } else {
                return "redirect:/userCenter";
            }
        } else {
            return "/exception";
        }

    }

    @RequestMapping("/weixinLogin")
    public String weixinLogin(Model model) {
        User user = PermissionContext.getUser();
        if (user != null) {
            // 判断用户名是否为空，如果为空，说明没有个人信息填写，跳转到完善信息的页面
            String username = user.getUsername();
            if (StringUtils.isBlank(username)) {
                List<Province> provinces = areaInfoService.getAllProvinces();
                model.addAttribute("provinces", provinces);
                return "/makeup";
            } else {
                // return "redirect:/userCenter";
                return "redirect:/queenIdx";
            }
        } else {
            return "/exception";
        }
    }



}
