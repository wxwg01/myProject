package com.alibaba.wanggang.sc2.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/4/26
 */
@RestController
public class Sc2Controller {

    @Value("${server.port}")
    private String port;


    @GetMapping("/port")
    public String getInfo(){
        return port;
    }

    @GetMapping("/server/info")
    public String getServerInfo(){
        Properties props=System.getProperties();
        return JSONObject.toJSONString(props);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public String discovery(){
        return JSONObject.toJSONString(discoveryClient.getServices().stream().map(it->discoveryClient.getInstances(it)).collect(Collectors.toList()));
    }
}
