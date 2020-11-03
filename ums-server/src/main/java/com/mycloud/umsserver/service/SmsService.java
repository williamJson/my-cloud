package com.mycloud.umsserver.service;

import java.util.Map;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/21
 */
public interface SmsService {

    void sendSms(String mobile, String smsType);


    void receiveMsg(Map msg);
}
