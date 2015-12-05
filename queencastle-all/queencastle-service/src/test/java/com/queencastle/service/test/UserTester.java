package com.queencastle.service.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.User;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class UserTester extends BaseTest {

    @Test
     @Ignore
    public void testInsert() {
        User user = new User();
        user.setPhone("1369907");
        user.setOutNick("悲伤逆流成河");
        user.setSource("weixin");
        user.setUsername("queencastle");
        user.setPassword("123456789");
        userService.insert(user);
    }

    @Test
    @Ignore
    public void testGetById() {
        String id = IdTypeHandler.encode(1L);
        User user = userService.getById(id);
        if (user != null) {
            System.out.println(user.getPhone());
            System.out.println(user.getOutNick());
            System.out.println(user.getUsername());
        }
    }

    @Test
    @Ignore
    public void testUpdate() {
        String id = IdTypeHandler.encode(1L);
        User user = new User();
        user.setId(id);
        user.setPhone("15068790227");
        userService.updateById(user);
    }

    @Test
    @Ignore
    public void testPageInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<User> pageInfo = userService.getUsersByParams(1, 10, map);
        if (pageInfo != null) {
            System.out.println(pageInfo.getRows().size());
        }
    }
}
