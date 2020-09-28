package com.mycloud.product.apis;

import org.springframework.stereotype.Component;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/9/28
 */
@Component
public class MemberFallback implements MemberFeignClient {

    @Override
    public String getMemberInfo(Long id) {
        return "getMemberInfo is full back";
    }
}
