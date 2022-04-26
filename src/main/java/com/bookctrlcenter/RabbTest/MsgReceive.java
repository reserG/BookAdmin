package com.bookctrlcenter.RabbTest;

import com.bookctrlcenter.Service.BookSer;
import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.MessageX;
import com.bookctrlcenter.entity.Util;
import com.bookctrlcenter.redis.RedisService;
import com.bookctrlcenter.util.MsgUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class MsgReceive {

    private static Logger log = LoggerFactory.getLogger(MsgReceive.class);

    @Autowired
    RedisService redisService;
    @Autowired
    BookSer bookSerImp;

    @RabbitListener(queues = RabbitConf.TESTQUEUE)
    public void receiveTest(Message message, Channel channel) throws IOException {
        System.out.println("收到消息");
        System.out.println(message.getBody());
        System.out.println(RedisService.beanToString(message.getBody()));
        System.out.println(message.getMessageProperties());
        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
        System.out.println(message.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = RabbitConf.BOOK_ADMIN)
    public void receive(Message message, Channel channel) {

        System.out.println("收到消息！！！");
        log.info("receive message:" + message);
//        MessageX mm = RedisService.stringToBean(message, MessageX.class);
        MessageX mm = MsgUtil.msgToObj(message, MessageX.class);
        System.out.println("msg  " +mm.toString());
        Util util = mm.getUtil();
        String key = mm.getKey();
        Book book = new Book();
        book.setUtil(util);
        String arr[] = key.split("UID");
        book.setIsbn(arr[0].substring(6));
        System.out.println("userid   " + arr[1].split("DP")[0]);
        book.getUtil().setUserID1(arr[1].split("DP")[0]);
        book.setDepartmentID(arr[1].split("DP")[1]);
        System.out.println("admin");
        System.out.println("setDepartmentID" + book.getDepartmentID());
        book.setCount(bookSerImp.getStuCount(book));
        System.out.println("count    " + book.getCount());
        try {
            if (bookSerImp.updateForAdmin(book) == 1) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("receive     " + message);
    }

//    @RabbitListener(queues = RabbitConf.BOOK_ADMIN)
//    public void delMsg(Message message, Channel channel) {
//
//
//        try {
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}
