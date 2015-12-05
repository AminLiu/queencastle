package com.queencastle.service.test.weixxin;

import org.junit.Test;

import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.utils.WeiXinHelper;

public class WeiXinTester {

    @Test
    // @Ignore
    public void test() {
        Object accessToken =
                WeiXinHelper.getAccessToken(GlobalValue.WEIXIN_APPID, GlobalValue.WEIXIN_SECRET);
        System.out.println(accessToken);

    }
}
