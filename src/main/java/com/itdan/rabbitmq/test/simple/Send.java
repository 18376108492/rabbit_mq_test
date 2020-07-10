package com.itdan.rabbitmq.test.simple;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *  简单队列测试
 *  数据发送方
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        /**
         * 队列声明参数
         * 1.队列名称
         * 2.
         * 3.
         * 4.
         * 5.
         */
        channel.queueDeclare(MQProperties.SIMPLE_QUEUE_NAME,false,false,false,null);

        //消息体
        String msg= "simple msg test";

        /**
         * 发送消息参数
         * 1.
         * 2.队列名称(key)
         * 3.
         * 4.
         *
         */
        System.out.println("----------开始发送消息-----------");

        channel.basicPublish("",MQProperties.SIMPLE_QUEUE_NAME,null,msg.getBytes());

        System.out.println("----------发送消息成功-----------");

        //关闭资源
        channel.close();
        connection.close();
    }
}
