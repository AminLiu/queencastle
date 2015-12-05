package com.queencastle.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.queencastle.dao.model.relations.RelationType;
import com.queencastle.dao.model.relations.UserRelation;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class UserRelationTest extends BaseTest {

    @Test
    public void test() {
        List<String> list = new ArrayList<String>();

        // list.add("1,2");
        // list.add("2,4");
        // list.add("4,5");
        // list.add("5,1");
        // list.add("5,2");
        // list.add("1,3");
        // list.add("3,2");
        // list.add("3,1");
        // list.add("5,6");

        list.add("6,9");
        list.add("19,6");
        list.add("20,19");
        for (String str : list) {
            try {
                UserRelation ur = new UserRelation();
                String[] array = str.split(",");
                String pid = IdTypeHandler.encode(Long.parseLong(array[0]));
                String uid = IdTypeHandler.encode(Long.parseLong(array[1]));
                ur.setParentId(pid);
                ur.setUserId(uid);
                ur.setType(RelationType.recommend);
                userRelationService.checkAndInsert(ur);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
