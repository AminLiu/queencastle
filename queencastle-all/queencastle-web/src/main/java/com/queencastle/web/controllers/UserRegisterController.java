package com.queencastle.web.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.model.User;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.dao.utils.jedis.StringJedisCache;
import com.queencastle.service.helper.PermissionHelper;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.sessions.PermissionContext;

/**
 * 用户注册
 * 
 * @author YuDongwei
 *
 */
@Controller
public class UserRegisterController {
    private static Logger logger = LoggerFactory.getLogger(UserRegisterController.class);
    @Autowired
    private StringJedisCache sessionIdCache;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectJedisCache sessionContextCache;

    /**
     * 系统中用户名唯一，这里要判断是否重复
     * 
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/isRepeated")
    public boolean isRepeated(String username) {
        List<User> users = userService.getByUsername(username);
        if (!users.isEmpty() && users.size() >= 1) {
            return true;
        }
        return false;
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 注册和登录非常相似，多了用户信息的录入过程
     * 
     * @param request
     * @param response
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/registerUser")
    public String register(HttpServletRequest request, HttpServletResponse response,
            String username, String password, Model model) {
        // 如果有过期的session先清理掉
        PermissionHelper.clearSessionIdFromCookie(request, response, sessionIdCache);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            String errorMessage = "用户名或密码为空!";
            model.addAttribute("info", errorMessage);
            return "login";
        } else {
            // 判断用户名是否重复，虽然发生意外请求的概率非常低，但是还是需要验证
            if (isRepeated(username)) {
                String errorMessage = "您注册的用户名已存在!";
                model.addAttribute("info", errorMessage);
                return "register";
            } else {
                // 加密用户密码
                // password = MD5Util.MD5Encode(password);
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setSource("web");
                user.setOutNick(username);
                user.setPhone("");
                userService.insert(user);
                logger.info("register userId:" + user.getId());
                PermissionHelper.generateLoginedSession(request, response, user, sessionIdCache,
                        sessionContextCache);
                PermissionContext.setUser(user);
            }
        }


        return "redirect:sysmain";
    }
}
