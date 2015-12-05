package com.queencastle.service.test;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.relations.RelationType;
import com.queencastle.dao.model.relations.UserRelation;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class UserRelationTester extends BaseTest {
    @Test
    public void test() {
        // UserRelation ua = new UserRelation();
        // ua.setParentId(IdTypeHandler.encode(1L));
        // ua.setUserId(IdTypeHandler.encode(2L));
        // ua.setType(RelationType.recommend);
        //
        // if (userRelationService.check(ua)) {
        //
        // userRelationService.insert(ua);
        // System.out.println("================正确");
        // } else {
        // System.out.println("================错误");
        // }

        // UserRelation ua = new UserRelation();
        // ua.setParentId(IdTypeHandler.encode(1L));
        // ua.setUserId(IdTypeHandler.encode(3L));
        // ua.setType(RelationType.recommend);
        //
        // if (userRelationService.check(ua)) {
        //
        // userRelationService.insert(ua);
        // System.out.println("================正确");
        // } else {
        // System.out.println("================错误");
        // }
        //
        // UserRelation ua = new UserRelation();
        // ua.setParentId(IdTypeHandler.encode(2L));
        // ua.setUserId(IdTypeHandler.encode(9L));
        // ua.setType(RelationType.recommend);
        //
        // if (userRelationService.check(ua)) {
        //
        // userRelationService.insert(ua);
        // System.out.println("================正确");
        // } else {
        // System.out.println("================错误");
        // }

        // UserRelation ua = new UserRelation();
        // ua.setParentId(IdTypeHandler.encode(3L));
        // ua.setUserId(IdTypeHandler.encode(2L));
        // ua.setType(RelationType.recommend);
        //
        // if (userRelationService.check(ua)) {
        //
        // userRelationService.insert(ua);
        // System.out.println("================正确");
        // } else {
        // System.out.println("================错误");
        // }

        // UserRelation ua = new UserRelation();
        // ua.setParentId(IdTypeHandler.encode(9L));
        // ua.setUserId(IdTypeHandler.encode(1L));
        // ua.setType(RelationType.recommend);
        // System.out.println("================正确3333" + userRelationService.check(ua));
        // if (userRelationService.check(ua)) {
        //
        // userRelationService.insert(ua);
        // System.out.println("================正确");
        // } else {
        // System.out.println("================错误");
        // }
    }

    @Test
    @Ignore
    public void testInsert() {
        for (int i = 50; i < 100; i++) {
            UserRelation userRelation = new UserRelation();
            userRelation.setUserId(IdTypeHandler.encode(i));
            // 随机的方式保证前后逻辑的连贯
            userRelation.setParentId(IdTypeHandler.encode(RandomUtils.nextInt(1, i)));
            userRelation.setType(RelationType.recommend);
            userRelationService.insert(userRelation);
        }
    }

    @Test
    @Ignore
    public void testGetByParentId() {
        String parentId = IdTypeHandler.encode(2);
        List<UserRelation> list =
                userRelationService.getByParentIdAndType(parentId, RelationType.recommend);
        for (UserRelation ur : list) {
            System.out.println(ur.getType());
        }
    }

}
