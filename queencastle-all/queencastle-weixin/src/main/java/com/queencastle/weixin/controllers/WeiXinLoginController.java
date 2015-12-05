package com.queencastle.weixin.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.queencastle.dao.model.User;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.dao.utils.jedis.StringJedisCache;
import com.queencastle.service.helper.PermissionHelper;
import com.queencastle.service.impl.security.PermissionManage;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.sessions.PermissionContext;

/**
 * 微信登录
 * 
 * @author YuDongwei
 *
 */
@Controller
public class WeiXinLoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionManage permissionManage;
    @Autowired
    private StringJedisCache sessionIdCache;
    @Autowired
    private ObjectJedisCache sessionContextCache;

    /***
     * 用户登录
     * 
     */
    @RequestMapping("/userLogin")
    public String weixinLogin(HttpServletRequest request, HttpServletResponse response,
            String username, String password, Model model) {
        // logger.info(getClass().getResource("/").getPath() + "big.jpg");
        // 如果有过期的session先清理掉
        PermissionHelper.clearSessionIdFromCookie(request, response, sessionIdCache);
        // 如果用户存在，就重新生成新的cookie，存放客户端
        // MD5加密校验
        List<User> users = userService.getByUsername(username);
        if (users == null || users.size() == 0) {
            PermissionContext.clearThreadVariable();
            model.addAttribute("info", "该用户不存在");
            return "weixinlogin";
        } else {
            User user = users.get(0);
            if (StringUtils.equals(user.getPassword(), password)) {
                PermissionHelper.generateLoginedSession(request, response, user, sessionIdCache,
                        sessionContextCache);
                PermissionContext.setUser(user);
            } else {
                model.addAttribute("info", "密码错误");
                return "weixinlogin";
            }
        }
        // return "redirect:/userCenter";
        return "redirect:/queenIdx";
    }



}
