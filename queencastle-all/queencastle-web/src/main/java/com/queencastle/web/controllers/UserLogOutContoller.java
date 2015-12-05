package com.queencastle.web.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.dao.utils.jedis.StringJedisCache;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.impl.security.PermissionManage;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.utils.CookieUtil;

@Controller
public class UserLogOutContoller {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionManage permissionManage;
    @Autowired
    private StringJedisCache sessionIdCache;
    @Autowired
    private ObjectJedisCache sessionContextCache;

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = CookieUtil.getSesssionIdFromCookies(request);
        if (StringUtils.isBlank(sessionId)) {

        } else {
            sessionIdCache.clearKey(sessionId);
            sessionContextCache.clearKey(sessionId);
            clearValueInCookies(response, GlobalValue.LOGIN_SESSION_ID, sessionId);
        }
        return "login";
    }

    private void clearValueInCookies(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
