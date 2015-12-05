package com.queencastle.service.test;

import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.relations.UserGroup;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class UserGroupTester extends BaseTest {

    @Test
    @Ignore
    public void testNumber() {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMaximumIntegerDigits(3);
        format.setMinimumIntegerDigits(3);
        for (int i = 0; i < 100; i++) {
            int number = RandomUtils.nextInt(1, 100);
            System.out.println(format.format(number));
        }
    }

    @Test
    @Ignore
    public void testInsert() {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMaximumIntegerDigits(3);
        format.setMinimumIntegerDigits(3);
        for (int i = 0; i < 10; i++) {
            UserGroup ug = new UserGroup();
            ug.setCname("vip" + RandomStringUtils.randomAlphanumeric(20));
            String code = format.format(RandomUtils.nextInt(1, 150));
            ug.setCode("8002" + code);
            ug.setImg(null);
            ug.setProfile("profile");
            userGroupService.insert(ug);
        }
    }

    @Test
    @Ignore
    public void testGetById() {
        String id = IdTypeHandler.encode(25);
        UserGroup ug = userGroupService.getById(id);
        System.out.println(ug.getCname());
        System.out.println(ug.getCode());
    }

    @Test
    @Ignore
    public void testGetByCode() {
        String code = "8002";
        List<UserGroup> list = userGroupService.getByCode(code);
        for (UserGroup ug : list) {
            System.out.println(ug.getCode());
        }
    }
}
