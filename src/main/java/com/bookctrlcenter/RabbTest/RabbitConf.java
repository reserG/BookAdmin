package com.bookctrlcenter.RabbTest;

import com.bookctrlcenter.entity.MessageX;
import com.bookctrlcenter.redis.RedisService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    RedisService redisService;
    @Autowired
    SendeMsg sendeMsg;
    @Bean
    public RabbitTemplate getRabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getMessageConverter());

        //消息传送给交换机
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause) ->{
            if (ack){
                redisService.del(correlationData.getId());
                System.out.println("消息成功发送到交换机！！"+ correlationData + "   cause  "+ cause);
            }else {
                MessageX message = redisService.get(correlationData.getId(),MessageX.class);
                sendeMsg.sendMsg(message);
                System.out.println("消息发送交换机失败 " + correlationData + "   cause  "+ cause);
            }
        });

        //消息从交换机发送给队列失败，只有失败的时候才会调用
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey)->{
            System.out.println("消息发送队列-发送失败 " + exchange +" " + routingKey+" " +replyCode+" " +replyText+" " + message);
        });
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter getMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public static final String BOOK_ADMIN = "bookQueues";
    public static final String EXCHANGE_DIRECT = "direct.exc";
    public static final String ROUTING_KEY_BOOKQ = "routing.bookQueues";

    public static final String TESTQUEUE = "testqueue";
    public static final String ROUTING_KEY = "routing.key";


    @Bean
    public Queue queue() {
        return new Queue(BOOK_ADMIN, true);
    }

    @Bean
    public Queue queueTest() {
        return new Queue(TESTQUEUE, true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_DIRECT,true,false);
    }

    @Bean
    public Binding msgBinding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(ROUTING_KEY_BOOKQ);
    }

    @Bean
    public Binding msgBindingTest() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(ROUTING_KEY);
    }
}
