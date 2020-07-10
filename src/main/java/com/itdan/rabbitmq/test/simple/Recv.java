package com.itdan.rabbitmq.test.simple;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *  简单队列测试
 *  数据消费方
 */
public class Recv {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(MQProperties.SIMPLE_QUEUE_NAME,false,false,false,null);

        //创建消费队列
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //当消息进入到队列时，会触发该方法
                String msg = new String(body, "utf-8");
                System.out.println("消费者获取到的消息:" + msg);
            }
        };

        //监听队列
        channel.basicConsume(MQProperties.SIMPLE_QUEUE_NAME,true,consumer);


        //关闭资源
       // channel.close();
        //connection.close();

    }
}
