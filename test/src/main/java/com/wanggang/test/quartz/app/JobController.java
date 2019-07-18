/*
package com.wanggang.test.quartz.app;

import com.wanggang.test.quartz.domain.QuartzJobDetail;
import com.wanggang.test.quartz.service.QuartzJobDetailService;
import com.wanggang.test.quartz.utils.JobUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    @Autowired
    private JobUtil jobUtil;
    @Autowired
    private QuartzJobDetailService appQuartzService;


    //添加一个job
    @RequestMapping(value="/addJob",method= RequestMethod.POST)
    public String addjob(@RequestBody QuartzJobDetail appQuartz) throws Exception {
        appQuartzService.insertAppQuartzSer(appQuartz);
        return jobUtil.addJob(appQuartz);
    }

    //暂停job
    @RequestMapping(value="/pauseJob",method=RequestMethod.POST)
    public String pausejob(@RequestBody Integer[]quartzIds) throws Exception {
        QuartzJobDetail appQuartz=null;
        if(quartzIds.length>0){
            for(Integer quartzId:quartzIds) {
                appQuartz=appQuartzService.selectAppQuartzByIdSer(quartzId);
                jobUtil.pauseJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return "success pauseJob";
        }else {
            return "fail pauseJob";
        }
    }

    //恢复job
    @RequestMapping(value="/resumeJob",method=RequestMethod.POST)
    public String resumejob(@RequestBody Integer[]quartzIds) throws Exception {
        QuartzJobDetail appQuartz=null;
        if(quartzIds.length>0) {
            for(Integer quartzId:quartzIds) {
                appQuartz=appQuartzService.selectAppQuartzByIdSer(quartzId);
                jobUtil.resumeJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return "success pauseJob";
        }else {
            return "fail pauseJob";
        }
    }


    //删除job
    @RequestMapping(value="/deletJob",method=RequestMethod.POST)
    public String deletjob(@RequestBody Integer[]quartzIds) throws Exception {
        QuartzJobDetail appQuartz=null;
        for(Integer quartzId:quartzIds) {
            appQuartz=appQuartzService.selectAppQuartzByIdSer(quartzId);
            String ret=jobUtil.deleteJob(appQuartz);
            if("success".equals(ret)) {
                appQuartzService.deleteAppQuartzByIdSer(quartzId);
            }
        }
        return "success pauseJob";
    }

    //修改
    @RequestMapping(value="/updateJob",method=RequestMethod.POST)
    public String  modifyJob(@RequestBody QuartzJobDetail appQuartz) throws Exception {
        String ret= jobUtil.modifyJob(appQuartz);
        if("success".equals(ret)) {
            appQuartzService.updateAppQuartzSer(appQuartz);
            return "success pauseJob";
        }else {
            return "fail pauseJob";
        }
    }

    //暂停所有
    @RequestMapping(value="/pauseAll",method=RequestMethod.GET)
    public String pauseAllJob() throws Exception {
        jobUtil.pauseAllJob();
        return "success pauseJob";
    }

    //恢复所有
    @RequestMapping(value="/repauseAll",method=RequestMethod.GET)
    public String repauseAllJob() throws Exception {
        jobUtil.resumeAllJob();
        return "success pauseJob";
    }

}
*/
