package com.aliababa.wg.sa.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @author wanggang
 * @create 2018-06-12 9:05
 **/
@Component
public class AkkaFactory {

    private static volatile AkkaFactory instant;

    private  ActorSystem system;
    private List<ActorRef> actorRefs;

    public static String path;

    public static AkkaFactory getInstant() {
        if(instant ==null){
            instant = new AkkaFactory();
        }
        if(instant !=null && instant.system==null){
            System.out.println(path);
            instant.system = ActorSystem.create("ClusterSystem", ConfigFactory.parseFile(new File(path)));
        }
        return instant;
    }

}
