package com.wanggang.test.quartz.config;

import com.sun.org.apache.xml.internal.security.Init;
import com.wanggang.test.quartz.domain.QuartzJobDetail;
import com.wanggang.test.quartz.service.QuartzJobDetailService;
import com.wanggang.test.quartz.utils.JobUtil;
import com.wanggang.test.quartz.utils.SchedulerUtils;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wb-wg471966@alibaba-inc.com
 * @date : 2019/7/17
 */
@Component
public class SchedulerRunning {

    @Autowired
    private QuartzJobDetailService quartzJobDetailService;

    @Autowired
    private JobUtil jobUtil;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${enable:true}")
    private boolean enable;

    @PostConstruct
    public void init(){
        SchedulerUtils.enable=enable;
    }

    @Scheduled(fixedRate = 1000*60*5)
    public void doJob(){
        if(!enable){
            return;
        }

        List<QuartzJobDetail> jobs = quartzJobDetailService.getAll();

        // 替换短类名为完整类名
        Map<String, Job> collect = applicationContext.getBeansOfType(Job.class)
            .entrySet()
            .stream()
            .collect(Collectors.toMap(it -> it.getValue().getClass().getName(), Map.Entry::getValue));

        Set<String> stringSet = collect.keySet();
        // 过滤掉不存在的 结果是当前系统中符合条件的并且开启的任务
        jobs = jobs.stream()
            .filter(item->stringSet.contains(item.getJobName()))
            .peek(it->it.setClazz(collect.get(it.getJobName()).getClass()))
            .collect(Collectors.toList());

        Map<String, QuartzJobDetail> dbJobMap = jobs.stream().collect(
            Collectors.toMap(it -> SchedulerUtils.getJobKey(it.getJobName(), it.getJobGroup()),
                Function.identity()));

        // 遍历数据库任务 如果内存中对比缺少就开启
        for (QuartzJobDetail quartzJobDetail : jobs){
            String key = SchedulerUtils.getJobKey(quartzJobDetail.getJobName(), quartzJobDetail.getJobGroup());
            SchedulerUtils.jobMap.put(key, quartzJobDetail);
            // 启动
            if(!jobUtil.checkExist(quartzJobDetail.getJobName(), quartzJobDetail.getJobGroup())){
                try {
                    jobUtil.addJob(quartzJobDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                // 判断是否暂停，是否要开启
                try {
                    System.out.println(jobUtil.modifyJob(quartzJobDetail));
                    //System.out.println(jobUtil.deleteJob(quartzJobDetail));
                    //System.out.println(jobUtil.addJob(quartzJobDetail));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Set<JobKey> existKeys = jobUtil.getExistKeys();

        // 检查 数据库--内存 数据库没有而内存有的，需要关闭
        for (JobKey jobKey : existKeys){
            String key = SchedulerUtils.getJobKey(jobKey.getName(), jobKey.getGroup());
            QuartzJobDetail quartzJobDetail = dbJobMap.get(key);
            if(quartzJobDetail==null){
                // 数据库中没有
                try {
                    jobUtil.pauseJob(jobKey.getName(), jobKey.getGroup());
                    jobUtil.deleteJob(quartzJobDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                // 判断状态，是否暂停之类的
            }
        }
    }
}
