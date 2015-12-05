package com.queencastle.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.service.utils.WeiXinHelper;

public class WeiXinUserTester extends BaseTest {
    // {
    // "total":23000,
    // "count":10000,
    // "data":{"
    // openid":[
    // "OPENID1",
    // "OPENID2",
    // ...,
    // "OPENID10000"
    // ]
    // },
    // "next_openid":"OPENID10000"
    // }
    @Test
    @Ignore
    public void testGetAllUser() {
        String accessToken = weiXinService.getAccessToken();
        Map<String, Object> map = WeiXinHelper.getFirstPageUser(accessToken, restTemplate);
        List<String> allOpenIds = new ArrayList<String>();
        Integer sum = 0;
        Integer total = (Integer) map.get("total");
        System.out.println(total);
        Integer openIdCnt = (Integer) map.get("count");
        System.out.println(openIdCnt);
        String nextOpenId = (String) map.get("next_openid");
        System.out.println(nextOpenId);
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        List<String> openIds = (List<String>) data.get("openid");
        if (openIds != null) {
            allOpenIds.addAll(openIds);
            for (String openId : openIds) {
                System.out.println(openId);
            }
        }
        sum += total;
        while (sum < total) {
            Map<String, Object> tmpMap =
                    WeiXinHelper.getNextPageUser(accessToken, nextOpenId, restTemplate);
            Map<String, Object> tmpData = (Map<String, Object>) tmpMap.get("data");
            List<String> tmpDataOpenIds = (List<String>) tmpData.get("openid");
            if (tmpDataOpenIds != null) {
                allOpenIds.addAll(tmpDataOpenIds);
                for (String openId : tmpDataOpenIds) {
                    System.out.println(openId);
                }
            }
            total = (Integer) tmpMap.get("total");
            sum += total;
            nextOpenId = (String) tmpMap.get("next_openid");
        }

    }

    @Test
    public void testSyncUser() {
        weiXinService.syncUserWithOnLine();
    }

}
