package com.aliababa.wg.sa;

import com.aliababa.wg.sa.akka.AkkaFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/4/28
 */
@SpringBootApplication
public class ServerAddressApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerAddressApplication.class, args);

        AkkaFactory.getInstant();
    }
}
