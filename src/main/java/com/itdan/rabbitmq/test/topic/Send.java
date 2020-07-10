package com.itdan.rabbitmq.test.topic;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题模式
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
        channel.exchangeDeclare(MQProperties.TOPIC_EXCHANGE_NAME,"topic");

        //发送消息
        //String msg="添加商品测试 topic msg test";
        String msg="删除商品测试 topic msg test";
        channel.basicPublish(MQProperties.TOPIC_EXCHANGE_NAME,"product.delete",null,msg.getBytes());

        System.out.println("消息发送成功");
        //关闭资源
        channel.close();
        connection.close();
    }
}
