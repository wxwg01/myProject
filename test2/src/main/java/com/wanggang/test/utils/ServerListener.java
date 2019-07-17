package com.wanggang.test.utils;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wb-wg471966@alibaba-inc.com
 * @date : 2019/7/16
 */
@Component
public class ServerListener implements ServletContextListener {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JobUtil jobUtil;

    @Autowired
    private QuartzJobDetailService quartzJobDetailService;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        List<QuartzJobDetail> jobs = quartzJobDetailService.getAll();

        Map<String, Job> beansOfType = applicationContext.getBeansOfType(Job.class);
        Set<String> stringSet = beansOfType.keySet();

        for (String string:stringSet){
            Job bean = beansOfType.get(string);

            for (QuartzJobDetail job:jobs){
                if(bean.getClass().getName().equals(job.getJobName())){
                    job.setClazz(bean.getClass());
                    try {
                        jobUtil.addJob(job);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
