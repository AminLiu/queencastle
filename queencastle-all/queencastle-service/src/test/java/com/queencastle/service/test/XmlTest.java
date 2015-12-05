package com.queencastle.service.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.utils.JsonUtils;

public class XmlTest {

    @Test
    @Ignore
    public void test() {
        String toUser = "o0EQqtztBibckN5O-Lz1ivxkok8k";
        String fromUser = "gh_bc2dedbda9e2";
        String content = "杭州彬小沫网络科技有限公司";
        List<String> list = new ArrayList<String>();
        list.add("<xml>");
        list.add("<ToUserName><![CDATA[" + toUser + "]]></ToUserName>");
        list.add("<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>");
        list.add("<CreateTime>" + System.currentTimeMillis() + "</CreateTime>");
        list.add("<MsgType><![CDATA[text]]></MsgType>");
        list.add("<Content><![CDATA[" + content + "]]></Content>");
        list.add("</xml>");
        String result = StringUtils.join(list, " ");
        System.out.println(result);
    }

    @Test
    public void testJson() {
        String seceneId = "good job";
        Map<String, Object> secene = new HashMap<String, Object>();
        secene.put("scene_str", seceneId);
        Map<String, Object> action_info = new HashMap<String, Object>();
        action_info.put("scene", secene);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action_info", action_info);
        params.put("action_name", "QR_LIMIT_SCENE");
        try {
            System.out.println(JsonUtils.object2Json(params));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
