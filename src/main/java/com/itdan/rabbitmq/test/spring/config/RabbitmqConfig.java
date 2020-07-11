package com.itdan.rabbitmq.test.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 为了确保消息的一致性，我们需要重新定义rabbitmq的一些信息
 */
@Configuration
public class RabbitmqConfig {

    private Logger logger=LoggerFactory.getLogger(RabbitmqConfig.class);

    //队列名
    private static String MSG_TEST_QUEUE_NAME="msg_queue_test";
    //转换器名
    private static String MSG_TEST_EXCHANGE_NAME="msg_exchange_name";

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;//获取连接工厂

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(cachingConnectionFactory);
        //当生产者发送一条消息给消费者时，消费者收到消息，会给生产者一个确认的信息（回调操作）
        //确认消息有没有发送成功
        rabbitTemplate.setConfirmCallback(((data, key, cause) ->{
            String msgID=data.getId();
          if(key){
              //表示消息发送成功
              logger.info("消息发送成功,msgID:{}",msgID);
              //根据消息ID更新消息日志表信息，将状态修改为成功
              //updateMsgLog(msgID,1);
          }else {
              //消息发送失败

              logger.error("消息发送失败,msgID:{}",msgID);
          }
             //消息失败的原因：
            //1.消息投递失败
            //2.消息往队列去过程失败
          rabbitTemplate.setReturnCallback((msg,repCode,repText,exchange,routingKey)->{
              //2.消息往队列去过程失败
              logger.error("消息发送失败,msgID:{}",msgID);
          });

        } ));

        return rabbitTemplate;
    }

    //配置队列信息
    @Bean
    public Queue queue(){
        return new Queue(MSG_TEST_QUEUE_NAME,true);
    }

    //配置转发器
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MSG_TEST_EXCHANGE_NAME,true,false);
    }

    //将转发器与队列进行绑定
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
    }
}
