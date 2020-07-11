package com.itdan.rabbitmq.test.confirm;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 事务机制-confirm模式 单条数据发送
 *
 * 消息发送方
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(MQProperties.CONFIRM_QUEUE_NAME,false,false,false,null);

        //开启confirm 模式
        channel.confirmSelect();

        /*发送消息*/
        String msg="事务测试 tx confirm msg test";
        channel.basicPublish("",MQProperties.CONFIRM_QUEUE_NAME,null,msg.getBytes());
       //发送单条数据
       if(channel.waitForConfirms()){
           System.out.println("消息发送成功");
       }else {
           System.out.println("消息发送失败");
       }
            //关闭资源
            channel.close();
            connection.close();


    }
}
