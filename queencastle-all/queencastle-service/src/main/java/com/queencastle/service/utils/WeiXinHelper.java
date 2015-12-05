package com.queencastle.service.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.queencastle.dao.model.User;
import com.queencastle.dao.utils.DateUtils;
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
    public static String TOKEN_URL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    public static String TICKET_URL =
            "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    public static String OPEN_AUTH_URL =
            "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    public static String BASE_SERVER_URL = "http://www.queencastle.cn";
    public static String UNION_AUTH_URL =
            "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
    public static String OPENID_URL =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    public static String QRCODE_URL =
            "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";
    public static String GET_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";

    public static String TEMPLATE_URL =
            "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";


    public final static String UPLOAD_IMG_URL =
            "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=image";

    public final static String UPLOAD_FOREVER_IMG_URL =
            "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=%s";


    public final static String GET_USER_URL =
            "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s";

    public final static String GET_MORE_USER_URL =
            "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";

    public final static String ADD_FOREVER_NEWS =
            "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";

    public static List<String> getWelcomeContent() {
        List<String> contentList = new ArrayList<String>();
        contentList.add("  嗨，等你好久啦！欢迎关注女王城堡喔\ue41f~\n\n");
        contentList
                .add("  你是一位天生的创业者\ue14c，而我，就是你命中注定的伙伴\ue415 /:,@f--女王城堡！货源、培训、社群、BBS、微商大数据、系统软件\ue107... 想你所想，给你所需！\n");

        contentList.add("  开启<a href=\"http://www.queencastle.cn/register\">创业之旅</a>\n");
        contentList
                .add("  查看<a href=\"http://www.queencastle.cn/resources/infos/welcome.html\">城堡秘密</a>\n");
        contentList.add("  窥探<a href=\"http://www.queencastle.cn/queenIdx\">城堡内幕</a>\n");
        return contentList;
    }

    // {
    // "articles": [{
    // "title": TITLE,
    // "thumb_media_id": THUMB_MEDIA_ID,
    // "author": AUTHOR,
    // "digest": DIGEST,
    // "show_cover_pic": SHOW_COVER_PIC(0 / 1),
    // "content": CONTENT,
    // "content_source_url": CONTENT_SOURCE_URL
    // },
    // //若新增的是多图文素材，则此处应还有几段articles结构
    // ]
    // }
    // 参数 是否必须 说明
    // title 是 标题
    // thumb_media_id 是 图文消息的封面图片素材id（必须是永久mediaID）
    // author 是 作者
    // digest 是 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
    // show_cover_pic 是 是否显示封面，0为false，即不显示，1为true，即显示
    // content 是 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
    // content_source_url 是 图文消息的原文地址，即点击“阅读原文”后的URL

    public static void addForeverMaterial(String accessToken, RestTemplate restTemplate) {
        String url = String.format(ADD_FOREVER_NEWS, accessToken);
        Map<String, Object> params = new HashMap<String, Object>();
        // //////
    }



    /**
     * 模板代理消息
     * 
     * @param restTemplate
     * @param toUser
     * @param proxyUser
     * @param accessToken
     */
    public static void templateProxyMessage(RestTemplate restTemplate, String toUser,
            User proxyUser, String accessToken) {
        // String accessToken = getAccessToken(GlobalValue.WEIXIN_APPID, GlobalValue.WEIXIN_SECRET);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", toUser);
        params.put("template_id", "iZIWCZ4zxqrRRYHfQ8XOIYSpgPhb0bxNCbMSRfOHUyU");
        Map<String, String> first = new HashMap<String, String>();
        first.put("value", "恭喜您，有一名代理加入到您的团队");
        first.put("color", "#FF0000");
        Map<String, String> key1 = new HashMap<String, String>();
        key1.put("value", proxyUser.getOutNick());
        key1.put("color", "#C4C400");
        Map<String, String> key2 = new HashMap<String, String>();
        key2.put("value", proxyUser.getSex());
        key2.put("color", "#0000FF");
        Map<String, String> key3 = new HashMap<String, String>();
        key3.put("value", "1388888888");
        key3.put("color", "#008000");
        Map<String, String> remark = new HashMap<String, String>();
        remark.put("value", "请您及时和代理联系,加油！");
        remark.put("color", "#743A3A");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("first", first);
        data.put("keyword1", key1);
        data.put("keyword2", key2);
        data.put("keyword3", key3);
        data.put("remark", remark);
        params.put("data", data);
        String url = String.format(TEMPLATE_URL, accessToken);
        Map<String, Object> resultMap = restTemplate.postForObject(url, params, Map.class);
        if (resultMap != null) {
            for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
                System.out.println(entry.getKey() + "--->" + entry.getValue());
            }

        }
    }

    public static void templateMemberMessage(RestTemplate restTemplate, String toUser,
            User memberUser, String accessToken) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", toUser);
        params.put("template_id", "qngB8ea0eaf4y-6U7gT6L0ntPhXqK3FpZp2jH4iQgrI");
        Map<String, String> first = new HashMap<String, String>();
        first.put("value", "恭喜您，有新会员通过扫描您提供的二维码进入了女王城堡大Party~");
        first.put("color", "#000000");
        Map<String, String> key1 = new HashMap<String, String>();
        key1.put("value", memberUser.getId());
        key1.put("color", "#8A2BE2");
        Map<String, String> key2 = new HashMap<String, String>();
        key2.put("value", DateUtils.getCurrFullTime());
        key2.put("color", "#8A2BE2");

        Map<String, String> remark = new HashMap<String, String>();
        remark.put("value",
                "热烈欢迎您的朋友 " + memberUser.getOutNick() + " 加入我们女王城堡~ 从今往后" + memberUser.getOutNick()
                        + "在女王城堡留下的每一处踪迹，都与您密不可分哦~ ");
        remark.put("color", "#000000");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("first", first);
        data.put("keyword1", key1);
        data.put("keyword2", key2);
        data.put("remark", remark);
        params.put("data", data);
        String url = String.format(TEMPLATE_URL, accessToken);
        Map<String, Object> resultMap = restTemplate.postForObject(url, params, Map.class);
        if (resultMap != null) {
            for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
                System.out.println(entry.getKey() + "--->" + entry.getValue());
            }

        }
    }


    // POST数据格式：json
    // POST数据例子：{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
    // 或者也可以使用以下POST数据创建字符串形式的二维码参数：
    // {"action_name":"QR_LIMIT_STR_SCENE","action_info":{"scene": {"scene_str": "123"}}}
    // {"action_name":"QR_LIMIT_STR_SCENE","action_info":{"scene":{"scene_str":"good job"}},}

    public static String createPermanentQRCode(String accessToken, String seceneId,
            RestTemplate restTemplate) {
        String ticket = null;
        String url = String.format(QRCODE_URL, accessToken);
        Map<String, Object> secene = new HashMap<String, Object>();
        secene.put("scene_str", seceneId);
        Map<String, Object> action_info = new HashMap<String, Object>();
        action_info.put("scene", secene);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action_info", action_info);
        params.put("action_name", "QR_LIMIT_STR_SCENE");
        Map<String, String> resultMap = restTemplate.postForObject(url, params, Map.class);
        if (resultMap != null) {
            for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                String key = entry.getKey();
                if (StringUtils.equalsIgnoreCase(key, "ticket")) {
                    ticket = entry.getValue();
                }
            }

        }
        return ticket;
    }

    /**
     * 上传临时素材，有效期三天
     * 
     * @param accessToken
     * @param restTemplate
     */
    public static void uploadTmpImg(String accessToken, RestTemplate restTemplate) {
        // WeiXinHelper.class.getResource(WeiXinHelper.class.getResource("/").getPath() +
        // "big.jpg");
        WritableResource resource = new FileSystemResource(new File("D:/big.jpg"));

        String url = String.format(UPLOAD_IMG_URL, accessToken);

        MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
        data.add("media", resource);
        String result = restTemplate.postForObject(url, data, String.class);
        System.out.println(result);

    }

    /**
     * 上传永久图片，获取URL
     * 
     * @param accessToken
     * @param restTemplate
     */
    public static String uploadPersistImg(File file, String accessToken, RestTemplate restTemplate) {
        WritableResource resource = new FileSystemResource(file);

        String url = String.format(UPLOAD_FOREVER_IMG_URL, accessToken);

        MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
        data.add("media", resource);
        String result = restTemplate.postForObject(url, data, String.class);
        if (StringUtils.isNoneBlank(result)) {
            @SuppressWarnings("unchecked")
            Map<String, String> map = JsonUtils.json2Object(result, HashMap.class);
            String picUrl = map.get("url");
            return picUrl;
        }
        return null;

    }

    public static byte[] downloadFile(String url, RestTemplate restTemplate) {

        byte[] bytes = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet request = new HttpGet(url);
            RequestConfig requestConfig =
                    RequestConfig.copy(RequestConfig.custom().build()).build();
            request.addHeader("User-Agent", userAgent);
            request.setConfig(requestConfig);
            HttpContext httpContext = new BasicHttpContext();
            response = httpclient.execute(request, httpContext);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                bytes = EntityUtils.toByteArray(entity);
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



        return bytes;

    }

    public static byte[] getQRCodeFile(String ticket, RestTemplate restTemplate) {
        String url = String.format(GET_QRCODE_URL, ticket);
        byte[] bytes = downloadFile(url, restTemplate);
        return bytes;
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
            return String.format(OPEN_AUTH_URL, GlobalValue.WEIXIN_APPID, redirectUrl);
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
                logger.error("content--->" + content);
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

    public static String getOpenId(String code, String weixin_appid, String weixin_secret,
            RestTemplate restTemplate) {
        String openid = "";
        if (!StringUtils.isBlank(code)) {
            try {
                String weixinAuth = String.format(OPENID_URL, weixin_appid, weixin_secret, code);
                String result = restTemplate.getForObject(weixinAuth, String.class);
                Map<String, String> map = JsonUtils.json2Object(result, HashMap.class);
                openid = map.get("openid");
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return openid;

    }

    public static Map<String, Object> getFirstPageUser(String accessToken, RestTemplate restTemplate) {
        String url = String.format(GET_USER_URL, accessToken);
        Map<String, Object> map = restTemplate.getForObject(url, HashMap.class);
        return map;
    }

    public static Map<String, Object> getNextPageUser(String accessToken, String nextOpenId,
            RestTemplate restTemplate) {
        String url = String.format(GET_MORE_USER_URL, accessToken, nextOpenId);
        Map<String, Object> map = restTemplate.getForObject(url, HashMap.class);
        return map;
    }


}
