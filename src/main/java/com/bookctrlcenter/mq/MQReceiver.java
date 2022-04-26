//package com.bookctrlcenter.mq;
//
//
//import com.bookctrlcenter.Service.BookSer;
//import com.bookctrlcenter.entity.Book;
//import com.bookctrlcenter.entity.Message;
//import com.bookctrlcenter.entity.Util;
//import com.bookctrlcenter.redis.RedisService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MQReceiver {
//
//		private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
//
//		@Autowired
//		RedisService redisService;
//	@Autowired
//	BookSer bookSerImp;
//
//		@RabbitListener(queues=MQConfig.BOOK_ADMIN)
//		 public void receive(String message) {
//
//			System.out.println("收到消息！！！");
//			log.info("receive message:"+message);
//			Message mm  = RedisService.stringToBean(message, Message.class);
//			Util util = mm.getUtil();
//			String key = mm.getKey();
//			Book book = new Book();
//			book.setUtil(util);
//			String arr[] = key.split("UID");
//			book.setIsbn(arr[0].substring(6));
//			System.out.println("userid   " +arr[1].split("DP")[0]);
//			book.getUtil().setUserID1(arr[1].split("DP")[0]);
//			book.setDepartmentID(arr[1].split("DP")[1]);
//			System.out.println("admin");
//			System.out.println("setDepartmentID"   +book.getDepartmentID());
//			book.setCount(bookSerImp.getStuCount(book));
//			System.out.println("count    " +book.getCount());
//			bookSerImp.updateForAdmin(book);
//			System.out.println("receive     " + message);
//		}
//}
