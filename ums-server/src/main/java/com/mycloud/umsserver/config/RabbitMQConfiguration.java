package com.mycloud.umsserver.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明：
 * rabbitMQ配置类
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/21
 */
@Configuration
public class RabbitMQConfiguration {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory);
        //setReturnCallback需要开启mandatory
        //设置开启mandatory，才能触发回调函数，无论推送消息结果什么样都强制调用回调函数
        template.setMandatory(true);
        //针对confirmCallback和returnCallback的一些说明
        //消息发送至交换机 ack=true，否则ack=false returnCallback不会被调用
        //ack=true但是消息没有对应的queue接受，returnCallback返回错误信息，经常见的是no_route
        //消息成功发送，则只有confirmCallback被调用returnCallback不会被调用
        //在回调函数中可以使用spring的bean来处理这些异常情况，常见的是记录日志，如果是比较重要的消息，可以尝试重试
        template.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback==相关信息：" + correlationData);
            System.out.println("ConfirmCallback==确认信息：" + ack);
            System.out.println("ConfirmCallback==原因：" + cause);
        });

        template.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("ReturnCallback==信息内容：" + message);
            System.out.println("ReturnCallback==回应码：" + replyCode);
            System.out.println("ReturnCallback==回应信息：" + replyText);
            System.out.println("ReturnCallback==交换机：" + exchange);
            System.out.println("ReturnCallback==路由：" + routingKey);
        });
        return template;
    }
}
