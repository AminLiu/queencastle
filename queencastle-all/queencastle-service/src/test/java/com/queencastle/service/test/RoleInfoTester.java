package com.queencastle.service.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.RoleInfo;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class RoleInfoTester extends BaseTest {
    @Test
    // @Ignore
    public void testInsert() {

        RoleInfo roleInfo = new RoleInfo();
        String cname = "财务部";
        roleInfo.setCname(cname);
        roleInfo.setMemo("stone");
        roleService.insert(roleInfo);
    }

    @Test
    @Ignore
    public void testUpdateById() {

        String id = IdTypeHandler.encode(2L);
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setId(id);
        roleInfo.setCname("guangming");
        roleInfo.setMemo("small stone");
        roleService.updateById(roleInfo);
    }

    @Test
    @Ignore
    public void testGetById() {

        String id = IdTypeHandler.encode(1l);
        RoleInfo roleInfo = roleService.getById(id);
        if (roleInfo != null) {
            System.out.println(roleInfo.getIdRaw());
            System.out.println(roleInfo.getMemo());
            System.out.println(roleInfo.getCname());
        }

    }

    @Test
    @Ignore
    public void testPageInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<RoleInfo> pageInfo = roleService.getRoleByParams(1, 10, map);
        if (pageInfo != null) {
            System.out.println(pageInfo.getRows().size());
        }
    }
}
