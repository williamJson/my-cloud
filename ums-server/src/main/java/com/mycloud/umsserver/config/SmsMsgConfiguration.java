package com.mycloud.umsserver.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明：
 * 发送短信消息队列配置类
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/21
 */
@Configuration
public class SmsMsgConfiguration {


    /**
     * 短信队列名
     */
    public static final String SMS_MSG_QUEUE_NAME = "sms_queue";

    public static final String SMS_MSG_EXCHANGE_NAME = "sms_exg";

    public static final String SMS_MSG_ROUTING_KEY = "sms_send";

    /**
     * 短信消息队列
     *
     * @return
     */
    @Bean
    public Queue smsQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);
        return new Queue(SMS_MSG_QUEUE_NAME, true);
    }


    /**
     * 配置短信交换机
     *
     * @return
     */
    @Bean
    public DirectExchange smsDirectExchange() {
        return new DirectExchange(SMS_MSG_EXCHANGE_NAME, true, false);
    }


    /**
     * 将队列和交换机绑定，并指定路由key
     *
     * @return
     */
    @Bean
    public Binding smsBinding() {

        return BindingBuilder.bind(smsQueue()).to(smsDirectExchange()).with(SMS_MSG_ROUTING_KEY);

    }
}
