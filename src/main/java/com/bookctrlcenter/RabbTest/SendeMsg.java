package com.bookctrlcenter.RabbTest;

import com.bookctrlcenter.redis.RedisService;
import com.bookctrlcenter.util.MD5Util;
import com.bookctrlcenter.util.MsgUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendeMsg {
    @Autowired
    RedisService redisService;
    @Autowired
    RabbitConf rabbitConf;
    @Autowired
    RabbitTemplate rabbitTemplate;
    public void sendMsg(Object obj){
        CorrelationData correlationData = new CorrelationData(MD5Util.getRandomString(16));
        System.out.println("correlationData.getId()   " +correlationData.getId());
        redisService.set(correlationData.getId(),RedisService.beanToString(obj),0);
        rabbitTemplate = rabbitConf.getRabbitTemplate();
        rabbitTemplate.convertAndSend(RabbitConf.EXCHANGE_DIRECT,RabbitConf.ROUTING_KEY_BOOKQ, MsgUtil.objToMsg(obj),new CorrelationData(MD5Util.getRandomString(16)));
    }
    public void sendMsgTest(Object obj){
        rabbitTemplate = rabbitConf.getRabbitTemplate();
        rabbitTemplate.convertAndSend(RabbitConf.EXCHANGE_DIRECT,RabbitConf.ROUTING_KEY, MsgUtil.objToMsg(obj),new CorrelationData(MD5Util.getRandomString(16)));
    }
}
