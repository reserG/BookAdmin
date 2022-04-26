package com.bookctrlcenter.util;

import com.alibaba.fastjson.JSON;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

@Service
public class MsgUtil {

    public static Message objToMsg(Object o) {
        if (null == o) {
            return null;
        }
        Message message = MessageBuilder.withBody(JSON.toJSONString(o).getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        return message;
    }

    public static <T> T msgToObj(Message message, Class<T> tClass) {
        if (null == message || tClass == null){
            return null;
        }
        new String(message.getBody());
        String str = new String(message.getBody());
        T obj =JSON.toJavaObject(JSON.parseObject(str),tClass);
        return obj;
    }
}
