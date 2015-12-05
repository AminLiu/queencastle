package com.queencastle.service.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.User;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.utils.WeiXinHelper;

public class QRCodeTester extends BaseTest {

    @Test
    @Ignore
    public void testFirst() throws UnsupportedEncodingException {
        // accessToken
        // tlvfmsRWrNk9C2H_67br78w4K_hdGHHqm4hUjMpGB1EIz73j_9NaNrhAt-gB6QTXjqDWFPuQ5rwAklzJVkVjZi5eKiRGZ4dqCtf3eBRLcGU
        String accessToken =
                WeiXinHelper.getAccessToken(GlobalValue.WEIXIN_APPID, GlobalValue.WEIXIN_SECRET);
        System.out.println(accessToken);
        // ticket---->gQEd8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzFFejhHaFRseDYyX2Zoa3ZrMkFVAAIE41skVgMEAAAAAA==
        // url---->http://weixin.qq.com/q/1Ez8GhTlx62_fhkvk2AU
        String seceneId = "testFirst";
        String ticket = WeiXinHelper.createPermanentQRCode(accessToken, seceneId, restTemplate);
        if (StringUtils.isNoneBlank(ticket)) {
            ticket = URLEncoder.encode(ticket, "utf-8");
            String url = String.format(WeiXinHelper.GET_QRCODE_URL, ticket);
            System.out.println(url);
        }
    }

    @Test
    @Ignore
    public void testSecond() {

        String ticket =
                "gQEd8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzFFejhHaFRseDYyX2Zoa3ZrMkFVAAIE41skVgMEAAAAAA==";

        ticket =
                "gQGn8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzBVeVVIWWpsdmEzRTVCZzZfMlFVAAIEcZYlVgMEAAAAAA==";
        byte[] bytes = WeiXinHelper.getQRCodeFile(ticket, restTemplate);
        if (bytes != null) {
            String key = resourceUploadService.uploadBytes(bytes, null);
            System.out.println("insert..." + key);
        }
    }

    @Test
    @Ignore
    public void testTemplate() {
        String accessToken = weiXinService.getAccessToken();
        WeiXinHelper.uploadTmpImg(accessToken, restTemplate);
    }

    @Test
    @Ignore
    public void testTemplateMember() {
        String accessToken = weiXinService.getAccessToken();
        String toUser = "o0EQqtztBibckN5O-Lz1ivxkok8k";
        User memberUser = userService.getById(IdTypeHandler.encode(137));
        WeiXinHelper.templateMemberMessage(restTemplate, toUser, memberUser, accessToken);;
    }


    @Test
    @Ignore
    public void testOnlineData() {
        String accessToken =
                WeiXinHelper.getAccessToken("wx4e9d6391b7e18271",
                        "efa7ed17086ea82dd785498110e1c308");
        Map<String, Object> map = WeiXinHelper.getFirstPageUser(accessToken, restTemplate);
        weiXinService.analyseMap(map, accessToken);
    }

    @Test
    public void testPersistPic() throws Exception {
        String path = System.getProperty("java.io.tmpdir");
        // String url =
        // "http://wx.qlogo.cn/mmopen/OicuDlU9bp8E2o1ibTEXgCZtyxezOAQaiaicWpyMniaOAxY4rXtIc8eOwkyYxOM6uCRg28MMk6vLd9WNF42v912ibtpg/0";

        String url = GlobalValue.QINIU_HOST + "1446177687346";
        byte[] bytes = WeiXinHelper.downloadFile(url, restTemplate);


        File someFile = new File(path + System.currentTimeMillis() + ".jpg");
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bytes);
        fos.flush();
        fos.close();

        String accessToken = weiXinService.getAccessToken();
        WeiXinHelper.uploadPersistImg(someFile, accessToken, restTemplate);
    }



}
