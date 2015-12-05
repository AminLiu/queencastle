package com.queencastle.weixin.controllers.weixin;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.queencastle.dao.utils.SHA1;
import com.queencastle.service.config.GlobalValue;

/**
 * 微信回调接口，第一次和服务器通信使用后续使用其他接口
 * 
 * @see com.queencastle.weixin.controllers.weixin.WeiXinMsgController
 * @author yuDongwei
 *
 */
@Controller
public class OpenController {


    @RequestMapping("/weixinCallerBackFirst")
    public void weixin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String token = GlobalValue.WEIXIN_TOKEN;
        String[] str = {token, timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();

        // 确认请求来至微信
        if (digest.equals(signature)) {
            response.getWriter().print(echostr);
        }
    }

}
