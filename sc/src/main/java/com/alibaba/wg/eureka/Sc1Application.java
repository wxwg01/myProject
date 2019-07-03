package com.alibaba.wg.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/4/26
 */
 @EnableEurekaClient
 @EnableDiscoveryClient
 @SpringBootApplication
public class Sc1Application  {

    public static void main(String[] args) {
        SpringApplication.run(Sc1Application.class, args);
    }
}
