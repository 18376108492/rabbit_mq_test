package com.itdan.rabbitmq.test.spring.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddMsgService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 关联的添加操作，例如添加一条用户信息
     * 参数应为用户对象
     * 当添加用户信息时，我们需要往日志信息表中，添加一条信息记录
     * @return
     */
    @Transactional
    public boolean addMsg(){

        //调用用户UserMapper添加的一条用户信息
        //boolean result= userMapper.addUser(user);
        //if(result){
         //log.info("添加成功");
        // 当用户信息添加成功时，需要生成一条信息日志
        // MsgLog msgLog=new MsgLog;
        //添加消息ID，员工ID，routeKey,exchange,创建和更新时间和初始的重试时间
        //后面会加一个定时器去扫描信息记录,状态为投递中，如果次数没有超过三次，由定时器重新发送
        //消息状态改为投递中
        //发送消息 rabbitTemplate
       // return true;
        // }
        return false;
    }
}
