package com.itdan.rabbitmq.test.publish;

import com.itdan.rabbitmq.test.properties.MQProperties;
import com.itdan.rabbitmq.test.utils.ConnectionUtli;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订阅模式
 *
 * 消息消费方
 */
public class Recv01 {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取MQ连接对象
        Connection connection = ConnectionUtli.getConnection();
        //获取通道
       final   Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(MQProperties.PUBLISH_QUEUE_NAME_1,false,false,false,null);

        //绑定队列到交换机
        channel.queueBind(MQProperties.PUBLISH_QUEUE_NAME_1,MQProperties.EXCHANGE_NAME,"");

        System.out.println("消费一号启动");
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("消费一号获取到的消息:" + msg);
                    //手动应答
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            }
        };

        channel.basicConsume(MQProperties.PUBLISH_QUEUE_NAME_1,false,consumer);




    }
}
