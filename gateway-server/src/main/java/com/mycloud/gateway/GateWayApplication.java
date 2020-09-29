package com.mycloud.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/9/29
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class GateWayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GateWayApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
