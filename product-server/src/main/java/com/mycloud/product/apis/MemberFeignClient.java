package com.mycloud.product.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by wyq on 2020/9/26.
 *
 * @auther wyq
 * qq:342622023
 */
@FeignClient(name = "ums-server")
public interface MemberFeignClient {

    @GetMapping("/getMemberInfo/{id}")
    String getMemberInfo(@PathVariable("id") Long id);
}
