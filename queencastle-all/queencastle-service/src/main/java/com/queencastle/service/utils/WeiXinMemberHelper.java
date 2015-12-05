package com.queencastle.service.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

/**
 * 微信公众平台用户管理
 * 
 * @author YuDongwei
 *
 */
public class WeiXinMemberHelper {
    public static String GROUP_CREATE_URL =
            "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=%s";
    public static String GROUP_GETALL_URL =
            "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=%s";

    public static String GROUP_GET_USER_GROUP_URL =
            "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=%s";

    public static String GROUP_UPDATE_URL =
            "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=%s";

    public static String GROUP_MOVE_MEMBER_URL =
            "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=%s";

    public static String GROUP_BATCH_MOVE_URL =
            "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=%s";

    public static String GROUP_DELETE_URL =
            "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=%s";

    public static void createGroup(String groupName, String accessToken, RestTemplate restTemplate) {
        String url = String.format(GROUP_CREATE_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, String> group = new HashMap<String, String>();
        group.put("name", groupName);
        data.put("group", group);
        String result = restTemplate.postForObject(url, data, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static void getAllGroup(String accessToken, RestTemplate restTemplate) {
        String url = String.format(GROUP_GETALL_URL, accessToken);
        String result = restTemplate.postForObject(url, null, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static void getUserGroupInfo(String openId, String accessToken, RestTemplate restTemplate) {
        String url = String.format(GROUP_GET_USER_GROUP_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("openid", openId);
        String result = restTemplate.postForObject(url, data, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    // POST数据例子：{"group":{"id":108,"name":"test2_modify2"}}

    public static void updateGroupInfo(String groupId, String groupName, String accessToken,
            RestTemplate restTemplate) {
        String url = String.format(GROUP_UPDATE_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, String> group = new HashMap<String, String>();
        group.put("id", groupId);
        group.put("name", groupName);
        data.put("group", group);
        String result = restTemplate.postForObject(url, data, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    // POST数据例子：{"openid":"oDF3iYx0ro3_7jD4HFRDfrjdCM58","to_groupid":108}
    public static void moveMember(String openId, String toGroupId, String accessToken,
            RestTemplate restTemplate) {

        String url = String.format(GROUP_MOVE_MEMBER_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("openid", openId);
        data.put("to_groupid", toGroupId);
        String result = restTemplate.postForObject(url, data, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }

    // POST数据例子：{"openid_list":["oDF3iYx0ro3_7jD4HFRDfrjdCM58","oDF3iY9FGSSRHom3B-0w5j4jlEyY"],"to_groupid":108}
    public static void moveBatcherMember(List<String> openIds, String toGroupId,
            String accessToken, RestTemplate restTemplate) {
        String url = String.format(GROUP_BATCH_MOVE_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("openid_list", openIds);
        data.put("to_groupid", toGroupId);
        String result = restTemplate.postForObject(url, data, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }

    // POST数据例子：{"group":{"id":108}}
    public static void deleteGroup(String groupId, String accessToken, RestTemplate restTemplate) {
        String url = String.format(GROUP_DELETE_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, String> group = new HashMap<String, String>();
        group.put("id", groupId);
        data.put("group", group);
        String result = restTemplate.postForObject(url, data, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }



}
