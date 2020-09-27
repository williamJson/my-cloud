package com.mycloud.umsserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/9/26
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class UmsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UmsApplication.class).web(WebApplicationType.SERVLET).run(args);
    }


}
