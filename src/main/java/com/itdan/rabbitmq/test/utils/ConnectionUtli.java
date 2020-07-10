package com.itdan.rabbitmq.test.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * MQ连接工具类
 */
public class ConnectionUtli {

    /**
     * 获取MQ连接对象
     *
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();

        //设置参数
        factory.setHost("123.57.128.124");
        factory.setPort(5672);
        factory.setVirtualHost("/vhost_mmr");
        factory.setUsername("user_mmr");
        factory.setPassword("123");

        return factory.newConnection();
    }
}
