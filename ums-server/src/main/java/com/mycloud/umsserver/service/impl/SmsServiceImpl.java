package com.mycloud.umsserver.service.impl;

import com.google.common.collect.Maps;
import com.mycloud.comm.util.IdGenerator;
import com.mycloud.umsserver.config.SmsMsgConfiguration;
import com.mycloud.umsserver.service.SmsService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/21
 */
//@RabbitListener(queues = SmsMsgConfiguration.SMS_MSG_QUEUE_NAME)
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送短信验证码
     *
     * @param mobile  接受验证码手机号
     * @param smsType 验证码类型
     */
    @Override
    public void sendSms(String mobile, String smsType) {

        Map<String, String> msg = Maps.newHashMapWithExpectedSize(10);
        msg.put("msgId", IdGenerator.next() + "");
        msg.put("mobile", mobile);
        msg.put("smsType", smsType);
        //产生四位随机数
        int code = new Random().nextInt(10000) + 1000;
        msg.put("code", code + "");
        AmqpTemplate template;
        rabbitTemplate.convertAndSend(SmsMsgConfiguration.SMS_MSG_EXCHANGE_NAME, SmsMsgConfiguration.SMS_MSG_ROUTING_KEY, msg);
    }


//    @RabbitHandler
    @Override
    public void receiveMsg(Map msg) {
        System.out.println("1__receive msg:" + msg.toString());

    }
}
