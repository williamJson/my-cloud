package com.tcc.order.controller;

import com.tcc.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/3
 */
@RequestMapping("/order")
@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/pay/{userId}/{money}")
    public String orderPayment(@PathVariable("userId") Long userId, @PathVariable("money") Integer money) {
        orderService.tryOrder();
        return "ok";
    }
}
