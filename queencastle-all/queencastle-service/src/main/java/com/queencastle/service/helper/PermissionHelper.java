package com.queencastle.service.helper;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.queencastle.dao.model.User;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.dao.utils.jedis.StringJedisCache;
import com.queencastle.dao.vo.UserSession;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.utils.CookieUtil;

public class PermissionHelper {
    public static void clearSessionIdFromCookie(HttpServletRequest request,
            HttpServletResponse response, StringJedisCache sessionIdCache) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(GlobalValue.LOGIN_SESSION_ID)) {
                String sid = cookie.getValue();
                if (StringUtils.isNoneBlank(sid))
                    clearValueInCookies(response, GlobalValue.LOGIN_SESSION_ID, sid);
                // 缓存中清理掉
                sessionIdCache.clearKey(sid);
            }
        }


    }

    public static void clearValueInCookies(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }



    public static void generateLoginedSession(HttpServletRequest request,
            HttpServletResponse response, User user, StringJedisCache sessionIdCache,
            ObjectJedisCache sessionContextCache) {
        String sessionId = CookieUtil.getSesssionIdFromCookies(request);
        if (StringUtils.isNoneBlank(sessionId)) {
        }
        String uuid = UUID.randomUUID().toString();
        CookieUtil.putSessionIdInCookies(request, response, GlobalValue.LOGIN_SESSION_ID, uuid);
        sessionIdCache.setData(uuid, uuid);
        UserSession userSession = new UserSession();
        userSession.setUser(user);
        userSession.setAccessTime(System.currentTimeMillis());
        sessionContextCache.setObj(uuid, userSession);
    }



}
