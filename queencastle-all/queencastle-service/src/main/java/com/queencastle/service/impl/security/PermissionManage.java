package com.queencastle.service.impl.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.queencastle.dao.model.User;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.dao.utils.jedis.StringJedisCache;
import com.queencastle.dao.vo.UserSession;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.sessions.PermissionContext;
import com.queencastle.service.utils.CookieUtil;

@Component
public class PermissionManage {

    public static final long TIME_OUT = 1000 * 60 * 60 * 20;
    @Autowired
    private StringJedisCache sessionIdCache;
    @Autowired
    private ObjectJedisCache sessionContextCache;

    public String getSessionId(HttpServletRequest request) {
        String sessionId = request.getParameter(GlobalValue.LOGIN_SESSION_ID);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = CookieUtil.getSesssionIdFromCookies(request);
        }
        return sessionId;
    }

    public void updateUserSessionCache(String sessionId, User user) {
        if (StringUtils.isNoneBlank(sessionId)) {
            String cachedSesssion = sessionIdCache.getData(sessionId);
            if (StringUtils.isNoneBlank(cachedSesssion)) {
                UserSession userSession = (UserSession) sessionContextCache.getObj(cachedSesssion);
                if (userSession != null) {
                    long current = System.currentTimeMillis();
                    userSession.setAccessTime(current);
                    userSession.setUser(user);
                    sessionContextCache.setObj(cachedSesssion, userSession);
                }
            }
        }
    }

    public boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = getSessionId(request);
        if (StringUtils.isBlank(sessionId)) {
            return false;
        }
        // 从缓存中取出
        String cachedSesssion = sessionIdCache.getData(sessionId);
        if (StringUtils.isBlank(cachedSesssion)) {
            return false;
        }
        // 将用户和session绑定，组成线程级上下文环境
        UserSession userSession = (UserSession) sessionContextCache.getObj(cachedSesssion);
        if (userSession != null) {
            // 判断是否超时
            long current = System.currentTimeMillis();
            long delta = current - userSession.getAccessTime();
            if (delta > TIME_OUT) {
                sessionIdCache.clearKey(sessionId);;
                sessionContextCache.clearKey(cachedSesssion);
                return false;
            } else {
                userSession.setAccessTime(current);
                sessionContextCache.setObj(cachedSesssion, userSession);
            }

            PermissionContext.setUser(userSession.getUser());
            return true;
        }
        return false;
    }

}
