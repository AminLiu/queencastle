package com.queencastle.service.test.weixxin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.service.test.BaseTest;
import com.queencastle.service.utils.WeiXinMemberHelper;

public class WeiXinPlatformTester extends BaseTest {

    @Test
    @Ignore
    public void testCreateGroup() throws IOException {
        String accessToken = weiXinService.getAccessToken();
        WeiXinMemberHelper.createGroup("测试总群", accessToken, restTemplate);

    }

    @Test
    @Ignore
    public void testGetAllGroup() {
        String accessToken = weiXinService.getAccessToken();
        WeiXinMemberHelper.getAllGroup(accessToken, restTemplate);
    }

    @Test
    @Ignore
    public void testGetUserGroupInfo() {
        String accessToken = weiXinService.getAccessToken();
        WeiXinMemberHelper.getUserGroupInfo("o0EQqt-prAr6hWTrEgYc81hddlNI", accessToken,
                restTemplate);
    }

    @Test
    @Ignore
    public void testUpdateUserGroupInfo() {
        String accessToken = weiXinService.getAccessToken();
        WeiXinMemberHelper.updateGroupInfo("101", "江苏总群", accessToken, restTemplate);
    }

    //
    @Test
    // @Ignore
    public void testMoveUser() {
        String accessToken = weiXinService.getAccessToken();
        WeiXinMemberHelper.moveMember("o0EQqt0NCY3wR67UE09bCKSaMwzg", "103", accessToken,
                restTemplate);
    }

    @Test
    @Ignore
    public void testBatchMove() {
        String accessToken = weiXinService.getAccessToken();
        List<String> openIds = new ArrayList<String>();
        openIds.add("o0EQqtxHzsUXJuq89iOz_E_YvC7s");
        openIds.add("o0EQqt68xgp55-YHX09RPOa5n8T0");
        openIds.add("o0EQqtzUHEP1TR-_KCSJmb-k8WvE");
        WeiXinMemberHelper.moveBatcherMember(openIds, "103", accessToken, restTemplate);
    }

    @Test
    @Ignore
    public void testDeleteGroup() {
        String accessToken = weiXinService.getAccessToken();
        WeiXinMemberHelper.deleteGroup("103", accessToken, restTemplate);
    }



}
