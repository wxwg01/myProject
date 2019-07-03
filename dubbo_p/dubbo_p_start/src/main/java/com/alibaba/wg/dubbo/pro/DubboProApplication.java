package com.alibaba.wg.dubbo.pro;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/5/9
 */
@SpringBootApplication
@ConditionalOnClass
@EnableDubbo
public class DubboProApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProApplication.class, args);
    }
}
