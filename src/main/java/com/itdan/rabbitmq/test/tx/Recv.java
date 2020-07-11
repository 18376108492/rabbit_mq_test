package com.itdan.rabbitmq.test.tx;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *  事务机制
 *  数据消费方
 */
public class Recv {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(MQProperties.TX_QUEUE_NAME,false,false,false,null);

        //创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("事务消费者获取消息:" + msg);

            }
        };
        //监听队列
        channel.basicConsume(MQProperties.TX_QUEUE_NAME,true,consumer);
    }
}
