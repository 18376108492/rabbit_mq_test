package com.itdan.rabbitmq.test.rounding;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 路由模式
 *
 * 消息发送方
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //定义分发器
        channel.exchangeDeclare(MQProperties.ROUNDING_EXCHANGE_NAME,"direct");

        //发送消息
        String msg="test msg rounding";

         //String roundingKey="error";//定义路由的key
         String roundingKey="info";//定义路由的key
        channel.basicPublish(MQProperties.ROUNDING_EXCHANGE_NAME,roundingKey,null,msg.getBytes());

        System.out.println("消息发送成功");
        //关闭资源
        channel.close();
        connection.close();
    }
}
