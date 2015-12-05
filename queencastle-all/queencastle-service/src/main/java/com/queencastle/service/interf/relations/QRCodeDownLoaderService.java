package com.queencastle.service.interf.relations;

/**
 * 下载微信推荐连接二维码
 * 
 * @author YuDongwei
 *
 */
public interface QRCodeDownLoaderService {

    void downLoad(String ticket, String userId);
}
