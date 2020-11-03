package com.mycloud.umsserver.service.impl;

import com.mycloud.umsserver.config.SmsMsgConfiguration;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/21
 */
@Component
public class RabbitListenerTwo {

    @RabbitListener(queues = SmsMsgConfiguration.SMS_MSG_QUEUE_NAME)
    @RabbitHandler
    public void handler(Map msg, Message message, Channel channel) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + "2__receive msg:" + msg);
//        try {
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
