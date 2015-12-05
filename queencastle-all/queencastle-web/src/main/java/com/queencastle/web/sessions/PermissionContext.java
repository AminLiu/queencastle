package com.queencastle.web.sessions;

/**
 * 权限上下文信息，用以存储当前用户相关联的信息
 * 
 * @author YuDongwei
 *
 */
public class PermissionContext {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

    public static void clearThreadVariable() {
        threadLocal.remove();
    }
}
