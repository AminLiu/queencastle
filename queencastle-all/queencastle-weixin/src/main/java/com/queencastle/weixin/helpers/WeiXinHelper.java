package com.queencastle.weixin.helpers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.queencastle.dao.utils.JsonUtils;
import com.queencastle.service.config.GlobalValue;

/**
 * code取值如下
 * <ul>
 * <li>snsapi_userinfo</li>
 * <li>snsapi_base</li>
 * 
 * @author YuDongwei
 *
 */
public class WeiXinHelper {
    private static Logger logger = LoggerFactory.getLogger(WeiXinHelper.class);
    private static CloseableHttpClient httpclient = HttpClients.custom().build();


    static String userAgent =
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; InfoPath.2)";
    static String TOKEN_URL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    static String TICKET_URL =
            "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    static String OPEN_AUTH_URL =
            "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    // static String BASE_SERVER_URL = "http://www.queencastle.cn";
    static String BASE_SERVER_URL = "http://m.queen520.cn";
    static String UNION_AUTH_URL =
            "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    public static void main(String[] args) {
        System.out.println(getAuthUrl("/inviteFriend"));

    }

    public void testTokenAndTicket() {
        String appid = "wx46fa9c99ce2fe6ef";
        String secret = "1e1f9afd06c75cc61018596daa1ba1b9";
        String accessToken = getAccessToken(appid, secret);
        System.out.println(accessToken);
        String ticket = generateTicket(accessToken);
        System.out.println(ticket);
    }

    public static String getAccessToken(String appid, String secret) {
        String access_token = "";
        CloseableHttpResponse response = null;
        try {
            String url = String.format(TOKEN_URL, appid, secret);
            HttpGet request = new HttpGet(url);
            RequestConfig requestConfig =
                    RequestConfig.copy(RequestConfig.custom().build()).build();
            request.addHeader("User-Agent", userAgent);
            request.setConfig(requestConfig);
            HttpContext httpContext = new BasicHttpContext();
            response = httpclient.execute(request, httpContext);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String content = EntityUtils.toString(entity);
                if (StringUtils.isNoneBlank(content)) {
                    @SuppressWarnings("unchecked")
                    Map<String, String> map = JsonUtils.json2Object(content, HashMap.class);
                    access_token = map.get("access_token");
                }
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("close response error:" + e.getMessage());
                }
            }
        }
        return access_token;

    }

    public static String generateTicket(String accessToken) {
        String ticket = "";
        CloseableHttpResponse response = null;
        try {
            String url = String.format(TICKET_URL, accessToken);
            HttpGet request = new HttpGet(url);
            RequestConfig requestConfig =
                    RequestConfig.copy(RequestConfig.custom().build()).build();
            request.addHeader("User-Agent", userAgent);
            request.setConfig(requestConfig);
            HttpContext httpContext = new BasicHttpContext();
            response = httpclient.execute(request, httpContext);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String content = EntityUtils.toString(entity);
                if (StringUtils.isNoneBlank(content)) {
                    @SuppressWarnings("unchecked")
                    Map<String, String> map = JsonUtils.json2Object(content, HashMap.class);
                    ticket = map.get("ticket");
                }
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("close response error:" + e.getMessage());
                }
            }
        }
        return ticket;

    }


    public static String getAuthUrl(String path) {
        try {
            StringBuffer buffer = new StringBuffer(BASE_SERVER_URL);
            buffer.append(path);
            String redirectUrl = URLEncoder.encode(buffer.toString(), "utf-8");
            return String.format(OPEN_AUTH_URL, GlobalValue.WEIXIN_OPENID, redirectUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * * 获取用户信息和UnionID { "subscribe": 1, "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", "nickname":
     * "Band", "sex": 1, "language": "zh_CN", "city": "广州", "province": "广东", "country": "中国",
     * "headimgurl":
     * "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0"
     * , "subscribe_time": 1382694957, "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL" }
     * 
     * @param openid
     * @param access_token
     * @return
     */
    public static Map<String, Object> getWeixinUserInfoUnionID(String openid, String access_token) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        try {
            String url = String.format(UNION_AUTH_URL, access_token, openid);

            HttpGet request = new HttpGet(url);
            RequestConfig requestConfig =
                    RequestConfig.copy(RequestConfig.custom().build()).build();
            request.addHeader("User-Agent", userAgent);
            request.setConfig(requestConfig);
            HttpContext httpContext = new BasicHttpContext();
            CloseableHttpResponse response = httpclient.execute(request, httpContext);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String content = EntityUtils.toString(entity);
                content = new String(content.getBytes("ISO-8859-1"), "utf-8");
                if (StringUtils.isNoneBlank(content)) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = JsonUtils.json2Object(content, HashMap.class);
                    return map;
                }
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            return userMap;
        }
        return userMap;
    }

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] {GlobalValue.WEIXIN_TOKEN, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信

        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;

    }

    private static String byteToStr(byte[] byteArray) {

        String strDigest = "";

        for (int i = 0; i < byteArray.length; i++) {

            strDigest += byteToHexStr(byteArray[i]);

        }

        return strDigest;

    }

    private static String byteToHexStr(byte mByte) {

        char[] Digit =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        char[] tempArr = new char[2];

        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];

        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);

        return s;

    }

}
