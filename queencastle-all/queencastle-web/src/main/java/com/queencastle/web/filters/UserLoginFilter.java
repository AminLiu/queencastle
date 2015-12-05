package com.queencastle.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.queencastle.service.impl.security.PermissionManage;
import com.queencastle.service.sessions.PermissionContext;

/**
 * 用户登录过滤器
 * 
 * @author YuDongwei
 *
 */
public class UserLoginFilter extends OncePerRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(UserLoginFilter.class);

    private PermissionManage permissionManage;

    private static List<String> RESOURCE_URI = new ArrayList<String>();
    private static List<String> NOT_LOGIN_URI = new ArrayList<String>();
    static {
        RESOURCE_URI.add("/resources");
        NOT_LOGIN_URI.add("/favicon.ico");
        NOT_LOGIN_URI.add("/login");
        NOT_LOGIN_URI.add("/logout");
        NOT_LOGIN_URI.add("/register");
        NOT_LOGIN_URI.add("/registerUser");
        // //////////////////// for initialization
        // NOT_LOGIN_URI.add("/relations/syncUsers");
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        // 非资源请求
        if (uri.equals("/") || isAvoidLogin(uri)) {
            PermissionContext.clearThreadVariable();
            filterChain.doFilter(request, response);
            return;
        }
        boolean isLogin = getPermissionManage().isLogin(request, response);
        if (!isLogin) {
            String errorMessage = "您的帐号已失效或者已经在别的地方登录!!!";
            response.setContentType("text/html");
            response.getWriter()
                    .print("<script>document.location.href='/?errorMessage=" + errorMessage
                            + "'</script>");
            PermissionContext.clearThreadVariable();
            return;
        } else {
            filterChain.doFilter(request, response);
        }

    }

    private static boolean isAvoidLogin(String uri) {
        for (String path : RESOURCE_URI) {
            if (uri.startsWith(path)) {
                return true;
            }
        }

        for (String path : NOT_LOGIN_URI) {
            if (StringUtils.equals(path, uri)) {
                return true;
            }
        }
        return false;

    }

    private PermissionManage getPermissionManage() {
        if (this.permissionManage == null) {
            WebApplicationContext applicationContext =
                    WebApplicationContextUtils.getWebApplicationContext(getFilterConfig()
                            .getServletContext());
            AutowireCapableBeanFactory autowireCapableBeanFactory =
                    applicationContext.getAutowireCapableBeanFactory();
            this.permissionManage = autowireCapableBeanFactory.getBean(PermissionManage.class);
        }
        return this.permissionManage;
    }



}
