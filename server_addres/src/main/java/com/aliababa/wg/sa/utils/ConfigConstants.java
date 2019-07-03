package com.aliababa.wg.sa.utils;

import com.aliababa.wg.sa.akka.AkkaFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/4/28
 */
@Configuration
public class ConfigConstants {
    @Value("${path}")
    private void path(String path){
        AkkaFactory.path = path;
    }

}
