package com.itdan.rabbitmq.test.publish;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订阅模式
 *
 * 消息发送方
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(MQProperties.EXCHANGE_NAME,"fanout");

        //发送消息
        String msg="test exchange fanout";

        channel.basicPublish(MQProperties.EXCHANGE_NAME,"",null,msg.getBytes());

        System.out.println("消息发送成功");
        //关闭资源
        channel.close();
        connection.close();
    }
}
