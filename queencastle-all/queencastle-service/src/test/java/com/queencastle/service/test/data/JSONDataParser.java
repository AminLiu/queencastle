package com.queencastle.service.test.data;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.queencastle.dao.model.data.ChatRecord;
import com.queencastle.dao.model.data.ChatResult;
import com.queencastle.dao.utils.JsonUtils;

public class JSONDataParser {

    @Test
    public void testParse() throws Exception {
        File file = new File("E:/abc.txt");
        String line = FileUtils.readFileToString(file);

        ChatResult chatResult = JsonUtils.json2Object(line, ChatResult.class);

        System.out.println(chatResult.getErrcode());
        System.out.println(chatResult.getErrmsg());
        System.out.println(chatResult.getRetcode());
        List<ChatRecord> list = chatResult.getRecordlist();
        for (ChatRecord record : list) {
            System.out.println(record.getOpenid());
            System.out.println(record.getOpercode());
            System.out.println(record.getText());
            System.out.println(record.getTime());
            System.out.println(record.getWorker());
        }


    }

}
