package com.queencastle.service.test;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.relations.UserQRCode;
import com.queencastle.dao.mybatis.IdTypeHandler;

public class UserQRCodeTester extends BaseTest {

    @Test
    @Ignore
    public void insertTest() {
        String userId = IdTypeHandler.encode(2L);
        String ticket = "测试";
        String img = "qiniu.com";
        UserQRCode userQRCode = new UserQRCode();
        userQRCode.setUserId(userId);
        userQRCode.setTicket(ticket);
        userQRCode.setImg(img);
        userQRCodeService.insert(userQRCode);

    }

    @Test
    public void getByUserIdTest() {

        String userId = IdTypeHandler.encode(2L);
        UserQRCode userQRCode = userQRCodeService.getByUserId(userId);

        System.out.println("==========");
        System.out.println("id=" + userQRCode.getId() + "  userId=" + userQRCode.getUserId()
                + "  ticket=" + userQRCode.getTicket() + "  img=" + userQRCode.getImg()
                + "  creatTime=" + userQRCode.getCreatedAt());
        System.out.println("==========");

    }
}
