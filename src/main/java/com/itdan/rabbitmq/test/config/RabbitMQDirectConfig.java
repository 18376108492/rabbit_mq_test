package com.itdan.rabbitmq.test.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ-SpringBoot 配置类
 */
//@Configuration
public class RabbitMQDirectConfig {

    //定义转换器名称
    public   static String SPRING_MQ_EXCHANGE_NAME="spring_mq_exchange_name";

    //定义队列名称
    public static String SPRING_MQ_QUEUE_NAME="spring_mq_queue_name";

    //定义队列
    @Bean
    public Queue queue(){
        return new Queue(SPRING_MQ_QUEUE_NAME);
    }

    //定义转换器
    @Bean
    public DirectExchange directExchange(){
        /**
         * 参数含义：
         * 1.name-转换器名称
         * 2.durable-重启后是否有效
         * 3.autoDelete-长期未使用是否自动删除消息
         */
        return new DirectExchange(SPRING_MQ_EXCHANGE_NAME,true,false);
    }

    //将队列与转换器进行绑定
    @Bean
    public Binding binding(){
        /**
         * routingKey 三类型
         *  fanout:不处理路由键
         *  direct:处理路由键
         *  topic: 将路由键与某模式匹配
         */
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
    }

}
