package com.queencastle.service.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.queencastle.dao.utils.JsonUtils;

/**
 * 多客服接口
 * 
 * @author YuDongwei
 *
 */
public class KFAccountHelper {

    public static String KF_ADD_URL =
            "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=%s";

    public static String KF_UPDATE_URL =
            "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=%s";

    public static String KF_GETALL_URL =
            "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=%s";

    public static String KF_DEL_URL =
            "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=%s";


    public static String KF_SENDMSG_URL =
            "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

    public static String KF_GET_RECORD_URL =
            " https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token=%s";

    public static void addKFAccount(String account, String nickName, String password,
            String accessToken, RestTemplate restTemplate) {
        String url = String.format(KF_ADD_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("kf_account", account + "@nwcb2015");
        data.put("nickname", nickName);
        data.put("password", password);
        try {
            String result = restTemplate.postForObject(url, data, String.class);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateKFAccount(String account, String nickName, String password,
            String accessToken, RestTemplate restTemplate) {
        String url = String.format(KF_UPDATE_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("kf_account", account + "@nwcb2015");
        data.put("nickname", nickName);
        data.put("password", password);
        String result = restTemplate.postForObject(url, data, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void delAccount(String account, String nickName, String password,
            String accessToken, RestTemplate restTemplate) {
        String url = String.format(KF_DEL_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("kf_account", account + "@nwcb2015");
        data.put("nickname", nickName);
        data.put("password", password);
        Map<String, Object> result = restTemplate.postForObject(url, data, Map.class);
        try {
            String str = JsonUtils.object2Json(result);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static void getAllKFAccount(String accessToken, RestTemplate restTemplate) {
        String url = String.format(KF_GETALL_URL, accessToken);
        String result = restTemplate.postForObject(url, null, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // //{
    // "touser":"OPENID",
    // "msgtype":"text",
    // "text":
    // {
    // "content":"Hello World"
    // }
    // }
    public static void sendMsgByKF(String toUser, String content, String accessToken,
            RestTemplate restTemplate) {
        String url = String.format(KF_SENDMSG_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("touser", toUser);
        data.put("msgtype", "text");
        Map<String, Object> contentMap = new HashMap<String, Object>();
        contentMap.put("content", content);
        data.put("text", contentMap);
        String result = restTemplate.postForObject(url, data, String.class);
        try {
            // StringHttpMessageConverter中文乱码
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //
    // 参数 是否必须 说明
    // access_token 是 调用接口凭证
    // starttime 是 查询开始时间，UNIX时间戳
    // endtime 是 查询结束时间，UNIX时间戳，每次查询不能跨日查询
    // pagesize 是 每页大小，每页最多拉取50条
    // pageindex 是 查询第几页，从1开始

    public static void getChatRecords(long startTime, long endTime, int pageSize, int pageIdx,
            String accessToken, RestTemplate restTemplate) {
        String url = String.format(KF_GET_RECORD_URL, accessToken);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("starttime", startTime);
        data.put("endtime", endTime);
        data.put("pageindex", pageIdx);
        data.put("pagesize", pageSize);
        Map<String, Object> result = restTemplate.postForObject(url, data, Map.class);

    }
}
