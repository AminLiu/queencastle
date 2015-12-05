package com.queencastle.service.impl;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.queencastle.service.interf.ResourceUploadService;

@Service
public class ResourceUploadServiceImpl extends BaseService implements ResourceUploadService {

    String accessKey = "X1oeeXyY2AIRHIYEYiwoeGP6Pxe7kyn8vP8xRpcr";
    String secretKey = "7JXfNTe_OTkZLHZcTCpqndDIWOilQqdXBhymrkGk";
    String bucketName = "queen-images";

    @Override
    public String uploadFile(File file, String randKey) {
        UploadManager uploadManager = new UploadManager();
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        try {
            if (StringUtils.isBlank(randKey)) {
                randKey = System.currentTimeMillis() + "";
            }
            Response res = uploadManager.put(file, randKey, token);
            StringMap map = res.jsonToMap();
            String hash = (String) map.get("hash");
            if (StringUtils.isBlank(hash)) {
            }
            return randKey;
        } catch (QiniuException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String uploadBytes(byte[] data, String randKey) {
        UploadManager uploadManager = new UploadManager();
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        try {
            if (StringUtils.isBlank(randKey)) {
                randKey = System.currentTimeMillis() + "";
            }
            Response res = uploadManager.put(data, randKey, token);
            StringMap map = res.jsonToMap();
            String hash = (String) map.get("hash");
            if (StringUtils.isBlank(hash)) {
            }
            logger.info("hash----->" + hash);
            return randKey;
        } catch (QiniuException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
