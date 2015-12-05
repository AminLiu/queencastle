package com.queencastle.dao.model.weixin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 微信消息实体基类，负责制定基本属性，转化为XML的规则
 * 
 * @author YuDongwei
 *
 */
public abstract class BaseMessage {

    public static final String TEXT_TYPE = "text";
    public static final String NEWS_TYPE = "news";
    public static final String IMG_TYPE = "image";
    public static final String DKF_TYPE = "transfer_customer_service";
    // 接收方帐号（收到的OpenID）
    private String toUserName;
    // 开发者微信号
    private String fromUserName;
    // 消息创建时间 （整型）,在数据里是当前时间的毫秒数，不需要set get方法
    protected long createTime;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }



    /**
     * 消息类型，在子类实现
     * 
     * @return
     */
    public abstract String getMsgType();

    public abstract String getXmlBody();

    public final List<String> getXmlStart() {
        List<String> list = new ArrayList<String>();
        list.add("<xml>");
        list.add("<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>");
        list.add("<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>");
        list.add("<CreateTime>" + System.currentTimeMillis() + "</CreateTime>");
        list.add("<MsgType><![CDATA[" + getMsgType() + "]]></MsgType>");
        return list;
    }

    public final String getXmlEnd() {
        return "</xml>";
    }

    public final String getXmlContent() {
        List<String> list = new ArrayList<String>();
        list.addAll(getXmlStart());
        list.add(getXmlBody());
        list.add(getXmlEnd());
        return StringUtils.join(list, " ");
    }


}
