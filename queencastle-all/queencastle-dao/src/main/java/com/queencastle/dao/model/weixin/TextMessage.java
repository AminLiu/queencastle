package com.queencastle.dao.model.weixin;

/**
 * 文本消息
 * 
 * @author YuDongwei
 *
 */
public class TextMessage extends BaseMessage {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getMsgType() {
        return TEXT_TYPE;
    }

    @Override
    public String getXmlBody() {
        return "<Content><![CDATA[" + getContent() + "]]></Content>";
    }


}
