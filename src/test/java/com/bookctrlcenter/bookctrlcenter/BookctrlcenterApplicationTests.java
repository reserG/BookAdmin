package com.bookctrlcenter.bookctrlcenter;

import com.alibaba.fastjson.JSON;
import com.bookctrlcenter.RabbTest.RabbitConf;
import com.bookctrlcenter.RabbTest.SendeMsg;
import com.bookctrlcenter.dao.*;
import com.bookctrlcenter.entity.User;
//import com.bookctrlcenter.mq.MQConfig;
//import com.bookctrlcenter.mq.MQSender;
import com.bookctrlcenter.entity.Util;
import com.bookctrlcenter.redis.RedisService;
import com.bookctrlcenter.util.MD5Util;
import com.bookctrlcenter.util.MsgUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class BookctrlcenterApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    DataSource dataSource;

    @Autowired
    BookDao bookDao;

    @Autowired
    ClassDao classDao;

    @Autowired
    ClassSdDao classSdDao;

//    @Autowired
//    MQSender mqSender;

    @Test
    public void contextLoads() throws SQLException {
        //org.apache.tomcat.jdbc.pool.DataSource
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        System.out.println("11111111111111111111111111111111111111111111111111111111111111111");
        connection.close();

    }

    @Test
    void xx() {

    }

    @Test
    void login() {
        User user = new User();
        user.setUsername("123");
        System.out.println(userDao.login(user).getUsername() + userDao.login(user).getPassword());
    }

    @Test
    void xxx() {
        MD5Util md5Util = new MD5Util();
        String salt = "q5z6g1w9";
        String rad = md5Util.getRandomString(8);
        System.out.println(rad);
        System.out.println(md5Util.inputPassToFormPass("123123"));
        System.out.println(md5Util.formPassToDBPass(md5Util.inputPassToFormPass("123123"), rad));
        System.out.println(md5Util.inputPassToDbPass("123123", rad));
    }

    @Test
    void xxxx() {
//        System.out.println(bookDao.insertToDL(new Book("123",123,21,"123",12,600,"123","123",12,"123","123",123,123)));
//         System.out.println(bookDao.getDataCountFromDL(new Book()));
//         ClassSchedule classSchedule = new ClassSchedule();
//         classSchedule.setUserID(1);
//         User user = new User();
//         user.setId(1);
//         Book book = new Book();
//         book.setUser(user);
//         book.setSize(10);
        System.out.println(MD5Util.inputPassToFormPass("123123"));
        System.out.println(MD5Util.formPassToDBPass(MD5Util.inputPassToFormPass("123123"), "cuGQ7326"));
        System.out.println(MD5Util.inputPassToDbPass("123123", "cuGQ7326"));
//         System.out.println(userDao.getUserByUserID(1).getUsername());
//         System.out.println(bookDao.listAllByUser(book).size());
//        for ( User user : userDao.listAllCSByID(u)){
//            System.out.println(user.getId()+"       "+user.getUsername()+"   " +user.getClassSchedule().getId() + "   "+user.getClassSchedule().getClassName());
//        }
//         System.out.println(userDao.listAllCSByID(u));
//         System.out.println(classSdDao.listAll(classSchedule).size());
//         ClassInfo classInfo = new ClassInfo();
//         JSONObject jsonObject = new JSONObject();
//         String role []= new String[1];
//         role[0] = "111"
//         role.
//         jsonObject.put("type",role);


//         classInfo.setClassID(16002);
//         classInfo.setSettlement(1);
//         classInfo.setIssuingUserID(1);
//         classDao.update(classInfo);
//         System.out.println(classDao.searchBookForClass(classInfo).size());
//         for (Book book :classDao.searchBookForClass(classInfo) ){
//             System.out.println(book.getBookName()+"          "+book.getCountStock());
//         }
//         Book book = new Book();
//         book.setCount(100);
//         book.setIsbn("1233");
//         System.out.println(bookDao.updateBC(book));
//         System.out.println(bookDao.listAllFromDemandLibrary(new Book("123",123,21,"123",12,600,"123","123",12,"123","123",0,10)).size());
    }

    @Autowired
    JedisPool jedisPool;

    @Test
    void xxxxxx() {
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        jedis.set("aaa", "test");
        System.out.println(jedis.get("aaa"));
        System.out.println(jedis.get("age"));
    }

    @Autowired
    AmqpTemplate amqpTemplate;

    @Test
    void mq() {
        String msg = "{\"key\":\"bkADAD123332332UID3DPNo13\",\"util\":{\"message1\":\"5656\",\"status1\":2,\"status2\":0,\"status3\":0,\"time2\":\"2020-04-24 20:26:03\",\"userID1\":3,\"userID2\":2,\"userID3\":0,\"userID4\":0}}";
        for (int i = 0; i < 20; i++) {
//            amqpTemplate.convertAndSend(MQConfig.BOOK_ADMIN, msg);

        }
    }
//
//    @RabbitListener(queues=MQConfig.BOOK_ADMIN)
//    void re(String message) {
//        System.out.println("receive     " + message);
//    }


    @Test
    void beanString() {
        User user = new User();
        user.setUsername("hhh");
        user.setPassword("123213");
        user.setGender("男");
        String mm = RedisService.beanToString(user);
        System.out.println(mm);
        User user1 = RedisService.stringToBean(mm, User.class);
        System.out.println(user1.getUsername());
    }

    @Test
    void xxxxxxx() {
        String aa = "bkADAD123123132UID3DPNo13";
        String arr[] = aa.split("UID");
        System.out.println(arr[0]);
        System.out.println(arr[0].substring(6));
        System.out.println(arr[1].split("DP")[0]);
        System.out.println(arr[1].split("DP")[1]);
//        Book book = new Book();
//        book.setDepartmentID("No13");
//        bookDao.getStuCount(book);
//        System.out.println(bookDao.getStuCount(book));
    }

    @Test
    void a() {
        User uD = new User();
        uD.setId("T13001");
        User user = userDao.select(uD);
        JSON json = JSON.parseObject(JSON.toJSONString(user));
        JSON json1 = (JSON) JSON.toJSON(user);
        System.out.println(JSON.toJavaObject(json1, User.class));
    }


    @Test
    void mogo(){
//
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:templates/TestBean.xml");
        Util util = (Util) context.getBean("util");
        System.out.println(util.getMessage1());
    }

    @Autowired
    SendeMsg sendeMsg;
    @Test
    void pub(){
        System.out.println("发送消息");
        for (int i=0;i<50;i++)
        sendeMsg.sendMsg(new User());
    }

        @RabbitListener(queues=RabbitConf.TESTQUEUE)
    void re(Message message, Channel channel) throws IOException {
        System.out.println("receive     " + message);
            System.out.println("解析msg  " +MsgUtil.msgToObj(message,User.class).toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
