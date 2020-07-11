package com.itdan.rabbitmq.test.spring.controller;

import com.itdan.rabbitmq.test.spring.service.AddMsgService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 发送消息测试用例
 */
@Controller
public class MQTestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AddMsgService addMsgService;

    @RequestMapping("/test01")
    @ResponseBody
    public String sendMsgTest01(){
        rabbitTemplate.convertAndSend("spring_mq_queue_name","spring_mq_test01");
        return "发送消息成功";
    }

    /**
     * 确保消息一致性测试
     * @return
     */
    @RequestMapping("/test02")
    @ResponseBody
    public String sendMsgTest02(){
        boolean result = addMsgService.addMsg();
        if(result){
            return "发送消息成功";
        }
        return "发送消息失败";
    }
}
