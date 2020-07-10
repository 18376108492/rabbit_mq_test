package com.itdan.rabbitmq.test.properties;

/**
 * 自定义MQ基本属性
 */
public class MQProperties {

    //简单队列名
    public static String SIMPLE_QUEUE_NAME="test_simple_queue";

    //工作队列名（轮询分发）
    public static String WORK_ROUND_QUEUE_NAME="test_work_round_queue";

    //订阅模式-一号队列名
    public static String PUBLISH_QUEUE_NAME_1="test_publish_queue_1";

    //订阅模式-二号队列名
    public static String PUBLISH_QUEUE_NAME_2="test_publish_queue_2";

    //订阅模式-分发器
    public static String EXCHANGE_NAME="test_exchange";

    //路由模式-分发器
    public static String ROUNDING_EXCHANGE_NAME="test_rounding_exchange";

    //路由模式-一号队列名
    public static String ROUNDING_QUEUE_NAME_1="test_rounding_queue_1";

    //路由模式-二号队列名
    public static String ROUNDING_QUEUE_NAME_2="test_rounding_queue_2";

    //主题模式-分发器
    public static String TOPIC_EXCHANGE_NAME="test_topic_exchange";

    //主题模式-一号队列名
    public static String TOPIC_QUEUE_NAME_1="test_topic_queue_1";

    //主题模式-二号队列名
    public static String TOPIC_QUEUE_NAME_2="test_topic_queue_2";


}
