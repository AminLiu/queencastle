package com.queencastle.service.test.weixxin;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.utils.MD5Util;
import com.queencastle.service.test.BaseTest;
import com.queencastle.service.utils.KFAccountHelper;

public class DKFAccountTester extends BaseTest {
    @Test
    @Ignore
    public void testAddAccount() throws IOException {

        String accessToken = weiXinService.getAccessToken();
        KFAccountHelper.addKFAccount("nwcb006", "nwcb006", MD5Util.MD5Encode("admin123"),
                accessToken, restTemplate);
    }

    @Test
    @Ignore
    public void testUpdateAccount() throws IOException {

        String accessToken = weiXinService.getAccessToken();
        KFAccountHelper.updateKFAccount("nw002", "chaseecho2", MD5Util.MD5Encode("admin123"),
                accessToken, restTemplate);
    }

    @Test
    @Ignore
    public void testGetAllAccount() throws IOException {

        String accessToken = weiXinService.getAccessToken();
        KFAccountHelper.getAllKFAccount(accessToken, restTemplate);
    }

    @Test
    public void testSendKFMsg() {
        String accessToken = weiXinService.getAccessToken();

        KFAccountHelper
                .sendMsgByKF("o0EQqtztBibckN5O-Lz1ivxkok8k",
                        "<a href=\"http://www.queencastle.cn/\">恭喜您通过审核了！！！</a>", accessToken,
                        restTemplate);
    }



}
