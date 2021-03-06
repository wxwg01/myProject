package com.wanggang.test.quartz.utils;

import com.wanggang.test.quartz.domain.QuartzJobDetail;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("jobUtil")
public class JobUtil {
    @Autowired
    private Scheduler scheduler;


    /**
     * 新建一个任务
     *
     */
    public String addJob(QuartzJobDetail appQuartz) throws Exception  {

        if (!CronExpression.isValidExpression(appQuartz.getCronExpression())) {
            return "Illegal cron expression";   //表达式格式不正确
        }
        JobDetail jobDetail=null;
        //构建job信息
        //jobDetail = JobBuilder.newJob(DynamicJob.class).withIdentity(appQuartz.getJobName(), appQuartz.getJobGroup()).build();
        jobDetail = JobBuilder.newJob(appQuartz.getClazz()).withIdentity(appQuartz.getJobName(), appQuartz.getJobGroup()).build();

        //表达式调度构建器(即任务执行的时间,不立即执行)
        CronScheduleBuilder scheduleBuilder = getCronScheduleBuilder(appQuartz.getCronExpression());

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(appQuartz.getJobName(), appQuartz.getJobGroup())
            .withSchedule(scheduleBuilder).build();

        //传递参数
        if(appQuartz.getInvokeParam()!=null && !"".equals(appQuartz.getInvokeParam())) {
            trigger.getJobDataMap().put("invokeParam",appQuartz.getInvokeParam());
        }
        scheduler.scheduleJob(jobDetail, trigger);

        // pauseJob(appQuartz.getJobName(),appQuartz.getJobGroup());
        return "success";
    }
    /**
     * 获取Job状态
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public String getJobState(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(jobName, jobGroup);
        return scheduler.getTriggerState(triggerKey).name();
    }

    //暂停所有任务
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    //暂停任务
    public String pauseJob(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "fail";
        }else {
            scheduler.pauseJob(jobKey);
            return "success";
        }

    }

    //恢复所有任务
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    // 恢复某个任务
    public String resumeJob(String jobName, String jobGroup) throws SchedulerException {

        JobKey jobKey = new JobKey(jobName, jobGroup);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "fail";
        }else {
            scheduler.resumeJob(jobKey);
            return "success";
        }
    }

    //删除某个任务
    public String  deleteJob(QuartzJobDetail appQuartz) throws SchedulerException {
        JobKey jobKey = new JobKey(appQuartz.getJobName(), appQuartz.getJobGroup());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null ) {
            return "jobDetail is null";
        }else if(!scheduler.checkExists(jobKey)) {
            return "jobKey is not exists";
        }else {
            scheduler.deleteJob(jobKey);
            return "success";
        }

    }

    //修改任务
    public String  modifyJob(QuartzJobDetail appQuartz) throws SchedulerException {
        if (!CronExpression.isValidExpression(appQuartz.getCronExpression())) {
            return "Illegal cron expression";
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(appQuartz.getJobName(),appQuartz.getJobGroup());
        JobKey jobKey = new JobKey(appQuartz.getJobName(),appQuartz.getJobGroup());
        if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //表达式调度构建器,不立即执行
            CronScheduleBuilder scheduleBuilder = getCronScheduleBuilder(appQuartz.getCronExpression());

            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(scheduleBuilder).build();
            //修改参数
            if(!trigger.getJobDataMap().get("invokeParam").equals(appQuartz.getInvokeParam())) {
                trigger.getJobDataMap().put("invokeParam",appQuartz.getInvokeParam());
            }
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            return "success";
        }else {
            return "job or trigger not exists";
        }

    }

    public boolean checkExist(String name,String group){
        JobKey jobKey = new JobKey(name,group );
        try {
            return scheduler.checkExists(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Set<JobKey> getExistKeys(){
        try {
            return scheduler.getJobKeys(GroupMatcher.anyGroup());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return new HashSet<>();

    }

    private CronScheduleBuilder getCronScheduleBuilder(String cron){
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
        //CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionFireAndProceed();
        //CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionIgnoreMisfires();
        return scheduleBuilder;
    }

}
