package com.wanggang.test.quartz.utils;

import com.wanggang.test.quartz.domain.QuartzJobDetail;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wb-wg471966@alibaba-inc.com
 * @date : 2019/7/17
 */
public class SchedulerUtils {

    public static Map<String, QuartzJobDetail> jobMap = new ConcurrentHashMap<>();

    public static String getJobKey(String jobName,String jobGroup){
        return jobName+":"+jobGroup;
    }

    public static boolean enable = true;
}
