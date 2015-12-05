package com.queencastle.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.UserRoleInfo;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class UserRoleInfoTester extends BaseTest {

    @Test
    // @Ignore
    public void testInsert() {
        UserRoleInfo info = new UserRoleInfo();
        String id = IdTypeHandler.encode(2L);
        String id2 = IdTypeHandler.encode(3L);
        info.setUserId(id);
        info.setRoleId(id2);

        userRoleInfoService.insert(info);
    }

    @Test
    @Ignore
    public void testGetByUserId() {
        String userId = IdTypeHandler.encode(1L);
        List<UserRoleInfo> list = userRoleInfoService.getByUserId(userId);

        if (!CollectionUtils.isEmpty(list)) {
            for (UserRoleInfo info : list) {
                System.out.println(info.getRoleId());
            }
        }
    }

    // @Test
    // @Ignore
    // public void testGetMenuIdByUserId(){
    // String userId = IdTypeHandler.encode(1L);
    // List<RoleMenuInfo> list = userRoleInfoService.getMenuByUserId(userId);
    // if(!CollectionUtils.isEmpty(list)){
    // for(RoleMenuInfo info:list){
    // System.out.println(info.getMenuId());
    // }
    // }
    // }



    @Test
    @Ignore
    public void testUpdate() {
        UserRoleInfo info = new UserRoleInfo();
        String id = IdTypeHandler.encode(3L);
        String id2 = IdTypeHandler.encode(4L);
        info.setId("10");
        info.setUserId(id);
        info.setRoleId(id2);
        userRoleInfoService.updateById(info);
    }



    @Test
    @Ignore
    public void testGetById() {
        String id = IdTypeHandler.encode(3L);
        UserRoleInfo info = userRoleInfoService.getById(id);
        if (info != null) {
            System.out.println(info.getRoleId());
            System.out.println(info.getUserId());

        }
    }

    @Test
    @Ignore
    public void testPageInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<UserRoleInfo> pageInfo = userRoleInfoService.getUserRoleByParams(1, 10, map);
        if (pageInfo != null) {
            System.out.println(pageInfo.getRows().size());
        }
    }
}
