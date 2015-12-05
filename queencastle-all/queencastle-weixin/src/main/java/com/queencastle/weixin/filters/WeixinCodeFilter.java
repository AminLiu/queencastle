package com.queencastle.weixin.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.queencastle.service.impl.security.PermissionManage;
import com.queencastle.service.sessions.PermissionContext;

/**
 * 临时使用web层的逻辑进行处理
 * 
 * @author YuDongwei
 *
 */
public class WeixinCodeFilter extends OncePerRequestFilter {
    private PermissionManage permissionManage;
    private static List<String> NOT_LOGIN_URI = new ArrayList<String>();
    static {
        NOT_LOGIN_URI.add("/resources");
        NOT_LOGIN_URI.add("/favicon.ico");
        NOT_LOGIN_URI.add("/userLogin");
        NOT_LOGIN_URI.add("/logout");
        NOT_LOGIN_URI.add("/register");
        NOT_LOGIN_URI.add("/getByProvinceCode");
        NOT_LOGIN_URI.add("/weixinCallerBack");

    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // 如果是微信的接口回调，根据code获取用户信息，并放入当前上下文
        String uri = request.getRequestURI();
        // 非资源请求
        if (uri.equals("/") || isAvoidLogin(uri)) {
            PermissionContext.clearThreadVariable();
            filterChain.doFilter(request, response);
            return;
        }
        boolean isLogin = getPermissionManage().isLogin(request, response);
        if (!isLogin) {
            String errorMessage = "您的帐号已失效或者已经在别的地方登录!";
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
        for (String path : NOT_LOGIN_URI) {
            if (uri.startsWith(path)) {
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
