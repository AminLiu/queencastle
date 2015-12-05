package com.queencastle.dao.model.weixin;

public class DKFMessage extends BaseMessage {

    @Override
    public String getMsgType() {
        return DKF_TYPE;
    }

    @Override
    public String getXmlBody() {
        return "";
    }

}
