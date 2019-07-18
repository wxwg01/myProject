package com.wanggang.test.quartz.domain;

import lombok.Data;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wb-wg471966@alibaba-inc.com
 * @date : 2019/7/17
 */
@Data
public class QuartzJobDetail {
    private Integer quartzId;  //id  主键
    private String jobName;  //任务名称
    private String jobGroup;  //任务分组
    private String startTime;  //任务开始时间
    private String cronExpression;  //corn表达式
    private String invokeParam;//需要传递的参数

    private String state;// 状态
    private boolean runOnce=false;//
    private int runTimes=0;//

    private Class clazz;//系统


}
