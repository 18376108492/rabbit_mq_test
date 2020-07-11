package com.itdan.rabbitmq.test.tx;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 事务机制
 *
 * 消息发送方
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(MQProperties.TX_QUEUE_NAME,false,false,false,null);

        //发送消息
        String msg="事务测试 tx msg test";
        try{
            //开启事务
            //开启事务会降低消息的吞吐量,不建议使用
            channel.txSelect();
            channel.basicPublish("",MQProperties.TX_QUEUE_NAME,null,msg.getBytes());
            System.out.println("消息发送成功");
            //int i=1/0;//出现异常情况
            //提交事务
            channel.txCommit();
        }catch (Exception e){
            //发生异常后，回滚事务
            System.out.println("回滚事务");
            channel.txRollback();
            System.out.println(e.getMessage());
        }finally {
            //关闭资源
            channel.close();
            connection.close();
        }

    }
}
