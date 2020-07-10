package com.itdan.rabbitmq.test.workfair;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列模式 公平分发
 *
 * 消息发送方
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(MQProperties.WORK_ROUND_QUEUE_NAME, false, false, false, null);

        /**
         *  在没有收到消费者的确认收到信息之前，发送者不给下一个消费者发送消息
         */
        int prefetchCount=1;//限制给同一消费者发送的消息条数不超过一条
        channel.basicQos(prefetchCount);

        //发送消息
        for (int i = 0; i < 20  ; i++) {
            String msg = "work round msg test"+i;
            channel.basicPublish("",MQProperties.WORK_ROUND_QUEUE_NAME,null,msg.getBytes());
            System.out.println("发送第"+i+"条消息");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //关闭资源
        channel.close();
        connection.close();
    }
}
