package com.queencastle.service.sessions;

import java.util.HashMap;
import java.util.Map;

import com.queencastle.dao.model.User;
import com.queencastle.service.config.GlobalValue;

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

    public static void setUser(User user) {
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
        }
        map.put(GlobalValue.LOGIN_USER_ID, user);
        threadLocal.set(map);
    }

    public static User getUser() {
        Map map = (Map) threadLocal.get();
        if (map != null) {
            return (User) map.get(GlobalValue.LOGIN_USER_ID);
        }
        return null;
    }
}
