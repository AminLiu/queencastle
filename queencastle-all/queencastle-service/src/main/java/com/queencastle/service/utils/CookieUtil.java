package com.queencastle.service.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.queencastle.service.config.GlobalValue;

public class CookieUtil {
    public static String getSesssionIdFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (StringUtils.equals(cookie.getName(), GlobalValue.LOGIN_SESSION_ID)) {
                String sid = cookie.getValue();
                return sid;
            }
        }
        return null;
    }

    public static void putSessionIdInCookies(HttpServletRequest request,
            HttpServletResponse response, String loginSessionKey, String sessionId) {
        Cookie cookie = new Cookie(loginSessionKey, sessionId);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
