package com.tcc.order.service;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/3
 */
public interface OrderService {

    void tryOrder();

    void confirmOrder();

    void cancelOrder();
}
