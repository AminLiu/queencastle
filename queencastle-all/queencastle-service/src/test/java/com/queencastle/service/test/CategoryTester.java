package com.queencastle.service.test;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.goods.Category;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.dao.utils.PinYinUtils;

public class CategoryTester extends BaseTest {

    @Test
    @Ignore
    public void insert() {
        Category category = new Category();
        String cname = "首饰";
        String ename = PinYinUtils.fullPinyin(cname);
        category.setCname(cname);
        category.setEname(ename);
        categoryService.insert(category);
    }

    // public void insert1() {
    // DemandSupplyInfo demandSupplyInfo =new DemandSupplyInfo();
    //
    // demandSupplyInfo.setId("1");
    // demandSupplyInfo.setAmount(11);
    // demandSupplyInfo.setMemo(memo);
    // resourceUploadService.insert(category);
    // }
    @Test
    @Ignore
    public void updateCheck() {

        String id = IdTypeHandler.encode(30L);
        System.out.println(IdTypeHandler.decode("16"));
        // demandSupplyInfoService.updateCheck(1, id);
    }

    @Test
    @Ignore
    public void updataById() {
        String id = IdTypeHandler.encode(30L);
        String cname = "test";
        String fileName = "20158888888888888888";
        String profile = "这是一个测试";
        // userGroupService.updateById(id, cname, profile, fileName);
    }

    @Test
    public void getType() {
        // String infoId = IdTypeHandler.encode(13L);
        // String userId = IdTypeHandler.encode(91L);
        // PraiseType a = praiseInfoService.getTypeByUserId(userId, infoId);
        // System.out.println(a);
        // System.out.println("ssssssssss====");
    }
}
