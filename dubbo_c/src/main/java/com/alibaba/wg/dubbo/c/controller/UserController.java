package com.alibaba.wg.dubbo.c.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.wg.dubbo.pro.client.DubboService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/5/9
 */
@RestController
public class UserController {

    @Reference(version = "1.0.0")
    private DubboService dubboService;


    @GlobalTransactional(timeoutMills = 10*1000 ,name = "dubbo_c_tx")
    @GetMapping
    public String getUser(){
        return JSONObject.toJSONString(dubboService.getList("aa"));
    }

}
