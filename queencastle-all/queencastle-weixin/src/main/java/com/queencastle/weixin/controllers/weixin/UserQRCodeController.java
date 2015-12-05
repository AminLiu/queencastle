package com.queencastle.weixin.controllers.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.queencastle.dao.model.relations.UserQRCode;
import com.queencastle.service.config.GlobalValue;
import com.queencastle.service.interf.relations.QRCodeDownLoaderService;
import com.queencastle.service.interf.relations.UserQRCodeService;
import com.queencastle.service.interf.weixin.WeiXinService;
import com.queencastle.service.utils.WeiXinHelper;

@Controller
@RequestMapping("/qrcode")
public class UserQRCodeController {
    @Autowired
    private UserQRCodeService userQRCodeService;
    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private QRCodeDownLoaderService qRCodeDownLoaderService;

    @RequestMapping("/getByUserId")
    public String getByUserId(String userId, Model model) {
        UserQRCode userQRCode = userQRCodeService.getByUserId(userId);
        if (userQRCode != null) {
            String img = userQRCode.getImg();
            if (StringUtils.isNoneBlank(img)) {
                userQRCode.setImg(GlobalValue.QINIU_HOST + img);
            }
        } else {
            try {
                String accessToken = weiXinService.getAccessToken();
                String ticket = WeiXinHelper.createPermanentQRCode(accessToken, userId, restTemplate);
                if (StringUtils.isNoneBlank(ticket)) {
                    ticket = URLEncoder.encode(ticket, "utf-8");
                    String picUrl = String.format(WeiXinHelper.GET_QRCODE_URL, ticket);
                    // download QRCODE in non-block thread
                    qRCodeDownLoaderService.downLoad(ticket, userId);
                }
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        model.addAttribute("userQRCode", userQRCode);
        return "/qrcode/usercode";
    }
}
