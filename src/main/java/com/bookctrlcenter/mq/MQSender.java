//package com.bookctrlcenter.mq;
//
//import com.bookctrlcenter.entity.Message;
//import com.bookctrlcenter.redis.RedisService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.AmqpTemplate;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class MQSender {
//
//	private static Logger log = LoggerFactory.getLogger(MQSender.class);
//
//	@Autowired
//	AmqpTemplate amqpTemplate ;
//
//	public void sendMessage(Message mm) {
//		String msg = RedisService.beanToString(mm);
//		log.info("send message:"+msg);
//		for (int i =0;i<11;i++)
//		amqpTemplate.convertAndSend(MQConfig.BOOK_ADMIN, msg);
//	}
//	public void sendMessageForTest(Message mm) {
//		String msg = RedisService.beanToString(mm);
//		log.info("send message:"+msg);
//			amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
//	}
//
////	@RabbitListener(queues=MQConfig.BOOK_ADMIN)
////    void re(String message) {
////
////        System.out.println("receive     " + message);
////    }
//}
