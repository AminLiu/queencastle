package com.queencastle.weixin.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.queencastle.dao.model.User;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.dao.utils.jedis.StringJedisCache;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.helper.PermissionHelper;
import com.queencastle.service.impl.security.PermissionManage;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.sessions.PermissionContext;
import com.queencastle.service.utils.WeiXinHelper;

public class WeiXinApiFilter extends OncePerRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(WeiXinApiFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();

        if (url.startsWith("/weixinApi")) {
            String code = request.getParameter("code");
            if (StringUtils.isNoneBlank(code)) {
                String openId =
                        WeiXinHelper.getOpenId(code, GlobalValue.WEIXIN_APPID,
                                GlobalValue.WEIXIN_SECRET,
                                (RestTemplate) getBeanFromContainer(RestTemplate.class));
                if (!StringUtils.isBlank(openId)) {
                    setCurrentUser(request, response, openId);
                }
            } else {
                logger.error("code is null,go into dev mode");
                String userId = request.getParameter("userId");
                setDevCurrentUser(request, response, userId);
            }
        }
        filterChain.doFilter(request, response);
    }

    private <T> T getBeanFromContainer(Class<T> clazz) {
        WebApplicationContext webApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        T bean = (T) webApplicationContext.getBean(clazz);
        return bean;
    }

    private <T> T getBeanFromContainer(String beanName) {
        WebApplicationContext webApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        T bean = (T) webApplicationContext.getBean(beanName);
        return bean;
    }

    private void setCurrentUser(HttpServletRequest request, HttpServletResponse response,
            String openId) {
        User user = PermissionContext.getUser();
        if (user == null) {
            UserService userService = getBeanFromContainer(UserService.class);
            user = userService.loadUserByOpenIdAndSource("weixin", openId);
            PermissionContext.setUser(user);
            // 如果客户端cookie存在，使用客户端cookie，否则将数据写入cookie
            PermissionManage permissionManage = getBeanFromContainer(PermissionManage.class);
            if (permissionManage.isLogin(request, response)) {
                // 存在无需操作

            } else {
                // 不存在
                StringJedisCache sessionIdCache = getBeanFromContainer("sessionIdCache");
                ObjectJedisCache sessionContextCache = getBeanFromContainer("sessionContextCache");
                PermissionHelper.generateLoginedSession(request, response, user, sessionIdCache,
                        sessionContextCache);
            }
        }
    }

    private void setDevCurrentUser(HttpServletRequest request, HttpServletResponse response,
            String userId) {
        User user = PermissionContext.getUser();
        if (user == null) {
            UserService userService = getBeanFromContainer(UserService.class);
            user = userService.getById(userId);
            if (user != null) {
                PermissionContext.setUser(user);
            }
        }
    }


}
