package com.itdan.rabbitmq.test.workfair;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列模式 公平分发
 *
 * 消息消费方
 */
public class Recv02 {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(MQProperties.WORK_ROUND_QUEUE_NAME, false, false, false, null);

        System.out.println("消费二号启动");
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //手动应答
                    channel.basicAck(envelope.getDeliveryTag(),false);
                    System.out.println("消费二号获取到的消息:" + msg);
                }

            }
        };

        boolean basicACK=false;

        channel.basicConsume(MQProperties.WORK_ROUND_QUEUE_NAME,basicACK,consumer);

    }
}
