package com.alibaba.wg.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/4/26
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    static String url = "http://SERVICE-SERVER-INFO/port";

    @GetMapping("port")
    public String getPort(){
        return restTemplate.getForEntity(url, String.class).getBody();
    }
}
