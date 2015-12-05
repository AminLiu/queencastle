package com.queencastle.service.test;

import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.relations.MemberType;
import com.queencastle.dao.model.relations.UserGroup;
import com.queencastle.dao.model.relations.UserMember;
import com.queencastle.dao.mybatis.IdTypeHandler;

/**
 * 测试需求： 三个不同的群，其中两个群是同一个地区的，有一个人是这两个群的群主，每个群都有自己的管理员，另外一个是单独的群
 * 
 * @author YuDongwei
 *
 */
public class UserMemberTester extends BaseTest {


    @Test
    @Ignore
    public void testOneGroup() {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMaximumIntegerDigits(3);
        format.setMinimumIntegerDigits(3);


        String groupId = IdTypeHandler.encode(22);
        UserGroup ug = userGroupService.getById(groupId);
        String baseCode = ug.getCode();
        // 群主
        UserMember master = new UserMember();
        master.setGroupId(groupId);
        master.setCode(baseCode + format.format(1));
        master.setType(MemberType.master);
        // master.setUserId(IdTypeHandler.encode(RandomUtils.nextInt(1, 10)));
        master.setUserId(IdTypeHandler.encode(3));
        userMemberService.insert(master);
        // 管理员
        UserMember admin = new UserMember();
        admin.setGroupId(groupId);
        admin.setCode(baseCode + format.format(2));
        admin.setType(MemberType.admin);
        admin.setUserId(IdTypeHandler.encode(RandomUtils.nextInt(10, 20)));
        userMemberService.insert(admin);
        // 群成员
        for (int i = 30; i < 50; i++) {
            UserMember member = new UserMember();
            member.setGroupId(groupId);
            member.setCode(baseCode + format.format(i));
            member.setType(MemberType.member);
            member.setUserId(IdTypeHandler.encode(i + 40));
            userMemberService.insert(member);
        }
    }


    @Test
    @Ignore
    public void testMembers() {
        UserMember member =
                userMemberService.getById(IdTypeHandler.encode(RandomUtils.nextInt(1, 22)));
        System.out.println(member.getGroupId());
    }

    @Test
    @Ignore
    public void testGetByUserId() {
        String userId = IdTypeHandler.encode(37);
        List<UserMember> list = userMemberService.getByUserId(userId);
        for (UserMember member : list) {
            System.out.println(member.getCode());
        }
    }

    @Test
    @Ignore
    public void testGetByGroupId() {
        String groupId = IdTypeHandler.encode(15);
        List<UserMember> list = userMemberService.getByGroupId(groupId);
        for (UserMember member : list) {
            System.out.println(member.getCode());
        }
    }



}
