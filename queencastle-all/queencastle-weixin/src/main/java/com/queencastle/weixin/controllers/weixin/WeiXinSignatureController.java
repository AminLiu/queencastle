package com.queencastle.weixin.controllers.weixin;

import java.security.MessageDigest;
import java.util.Formatter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.utils.WeiXinHelper;
import com.queencastle.weixin.GlobalCode;
import com.queencastle.weixin.ResponseObject;

@Controller
public class WeiXinSignatureController {
    private static Logger logger = LoggerFactory.getLogger(WeiXinSignatureController.class);
    private static String PARAM_SIGN = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";

    @ResponseBody
    @RequestMapping("/getWeiXinSignature")
    public ResponseObject<String> getWeiXinSignature(String url, String timestamp, String noncestr) {
        ResponseObject<String> responseObject = new ResponseObject<String>();
        String accessToken =
                WeiXinHelper.getAccessToken(GlobalValue.WEIXIN_APPID, GlobalValue.WEIXIN_SECRET);
        if (StringUtils.isBlank(accessToken)) {
            responseObject.setCode(GlobalCode.ERROR);
            return responseObject;
        }
        String ticket = WeiXinHelper.generateTicket(accessToken);
        if (StringUtils.isBlank(ticket)) {
            responseObject.setCode(GlobalCode.ERROR);
            return responseObject;
        }
        String sign = String.format(PARAM_SIGN, ticket, noncestr, timestamp, url);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sign.getBytes("UTF-8"));
            String signature = byteToHex(crypt.digest());
            responseObject.setData(signature);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseObject.setCode(GlobalCode.ERROR);
        }
        return responseObject;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
