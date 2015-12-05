package com.queencastle.service.impl.relations;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.queencastle.dao.model.relations.UserQRCode;
import com.queencastle.dao.utils.ImageUtils;
import com.queencastle.service.interf.ResourceUploadService;
import com.queencastle.service.interf.relations.QRCodeDownLoaderService;
import com.queencastle.service.interf.relations.UserQRCodeService;
import com.queencastle.service.utils.WeiXinHelper;

@Service
public class QRCodeDownLoaderServiceImpl implements QRCodeDownLoaderService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ResourceUploadService resourceUploadService;

    @Autowired
    private UserQRCodeService userQRCodeService;

    @Override
    public void downLoad(final String ticket, final String userId) {
        taskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                byte[] bytes = WeiXinHelper.getQRCodeFile(ticket, restTemplate);
                if (bytes != null) {
                    try {
                        File bigFile = new File(getClass().getResource("/").getPath() + "big.jpg");
                        BufferedImage resizeImg = ImageUtils.reSize(bytes, 248, 248);
                        byte[] newBytes =
                                ImageUtils.fillRectanglePic(resizeImg, bigFile, 185, 396, 248, 248);
                        String key = resourceUploadService.uploadBytes(newBytes, null);
                        UserQRCode userQRCode = new UserQRCode();
                        userQRCode.setTicket(ticket);
                        userQRCode.setUserId(userId);
                        userQRCode.setImg(key);

                        userQRCodeService.insert(userQRCode);
                    } catch (IOException e) {
                        logger.info(e.getMessage());
                    }
                }

            }
        });

    }
}
