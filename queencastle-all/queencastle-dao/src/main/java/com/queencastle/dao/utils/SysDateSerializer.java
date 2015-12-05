package com.queencastle.dao.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 系统时间处理，在各种JSON传递的时候，在对应的字段加上这个注解即可，用法如下：<br>
 * 
 * @JsonSerialize(using = SysDateSerializer.class) private Date startTime;
 * @author 于东伟
 *
 */
public class SysDateSerializer extends JsonSerializer<Date> {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String dateStr = sdf.format(date);
        generator.writeString(dateStr);
    }

}
