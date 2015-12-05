package com.queencastle.dao.model.weixin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ImageMessage extends BaseMessage {

    private String mediaId;

    @Override
    public String getMsgType() {
        return IMG_TYPE;
    }

    @Override
    public String getXmlBody() {
        List<String> list = new ArrayList<String>();
        list.add("<Image>");
        list.add("<MediaId><![CDATA[" + getMediaId() + "]]></MediaId>");
        list.add("</Image>");
        return StringUtils.join(list, " ");
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
