package com.mycloud.umsserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wyq on 2020/9/26.
 *
 * @auther wyq
 * qq:342622023
 */

@RestController
public class MemberController {


    /**
     * 根据会员id获取会员详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getMemberInfo/{id}")
    public String getMemberInfo(@PathVariable("id") Long id) {
        System.out.println("this is member id=" + id);
        return id + "";
    }
}
