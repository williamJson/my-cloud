package com.mycloud.product.controller;

import com.mycloud.product.apis.MemberFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wyq on 2020/9/26.
 * 订单相关服务
 *
 * @auther wyq
 * qq:342622023
 */
@RestController
public class ProducetController {

    @Autowired
    private MemberFeignClient memberFeignClient;


    @GetMapping("/addCart")
    public String addCart() {
//        System.out.println("this is call remote result=" + memberFeignClient.getMemberInfo(123L));
        return memberFeignClient.getMemberInfo(123L);
    }
}
