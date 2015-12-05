package com.queencastle.service.test.groups;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.User;
import com.queencastle.service.test.BaseTest;

public class UserGroupMemberTester extends BaseTest {

    // 初始化系统里的用户信息:地区信息和会员审核信息
    @Test
    @Ignore
    public void testInit() {
        List<User> users = userService.getAllUserIds();
        for (User user : users) {
            userDetailInfoService.updateOrInsertForTest(user.getId());
            //
            userAuditService.updateOrInsert(user);
        }
    }

    @Test
    public void testSelfGroup() {
        User user = userService.getById("1vvby");
        userGroupService.selfBuildGroup("浙江省自建群", "", "自建群", user);

    }


}
