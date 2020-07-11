package com.itdan.rabbitmq.test.spring.receiver;

/**
 * 消息接收者
 */

import com.itdan.rabbitmq.test.config.RabbitMQDirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DircetReceiver {

    //监听队列
    @RabbitListener(queues = "spring_mq_queue_name")
    public void handler_01(String msg){
        System.out.println("handler_01接收到消息:"+ msg);
    }
}
