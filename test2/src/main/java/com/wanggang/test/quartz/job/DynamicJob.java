package com.wanggang.test.quartz.job;

import com.alibaba.edas.schedulerx.ProcessResult;
import com.alibaba.edas.schedulerx.ScxSimpleJobContext;
import com.alibaba.edas.schedulerx.ScxSimpleJobProcessor;
import com.alibaba.fastjson.JSONObject;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by EalenXie on 2018/6/4 14:29
 * :@DisallowConcurrentExecution : 此标记用在实现Job的类上面,意思是不允许并发执行.
 * :注意org.quartz.threadPool.threadCount线程池中线程的数量至少要多个,否则@DisallowConcurrentExecution不生效
 * :假如Job的设置时间间隔为3秒,但Job执行时间是5秒,设置@DisallowConcurrentExecution以后程序会等任务执行完毕以后再去执行,否则会在3秒时再启用新的线程执行
 */
@Component
public class DynamicJob implements ScxSimpleJobProcessor, Job {
    private Logger logger = LoggerFactory.getLogger(DynamicJob.class);
    /**
     * 核心方法,Quartz Job真正的执行逻辑.
     * @param executorContext executorContext JobExecutionContext中封装有Quartz运行所需要的所有信息
     * @throws JobExecutionException execute()方法只允许抛出JobExecutionException异常
     */
    @Override
    public void execute(JobExecutionContext executorContext) throws JobExecutionException {
        //JobDetail中的JobDataMap是共用的,从getMergedJobDataMap获取的JobDataMap是全新的对象
        JobDataMap map = executorContext.getMergedJobDataMap();
        System.out.println(JSONObject.toJSONString(map));

    }

    @Override
    public ProcessResult process(ScxSimpleJobContext simpleJobContext) {
        System.out.println(simpleJobContext.getJob().getJobArguments()+" process "+new Date());
        return new ProcessResult(true);
    }
}
