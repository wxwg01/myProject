package com.wanggang.test.quartz.service;

import com.wanggang.test.quartz.job.DynamicJob;
import com.wanggang.test.quartz.domain.QuartzJobDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wb-wg471966@alibaba-inc.com
 * @date : 2019/7/17
 */
@Service
public class QuartzJobDetailService {

    public void insertAppQuartzSer(QuartzJobDetail appQuartz) {
        System.out.println("yeah");
    }

    public QuartzJobDetail selectAppQuartzByIdSer(int id) {
        return getNewJob();
    }

    public void deleteAppQuartzByIdSer(Integer quartzId) {
    }

    public void updateAppQuartzSer(QuartzJobDetail appQuartz) {
    }

    public List<QuartzJobDetail> getAll() {

        return new ArrayList<QuartzJobDetail>() {{
            this.add(getNewJob());
        }};
    }

    QuartzJobDetail getNewJob() {
        QuartzJobDetail job = new QuartzJobDetail();
        job.setInvokeParam("com.wanggang.test");
        job.setJobName(DynamicJob.class.getName());
        job.setJobGroup("cdmp");
        job.setCronExpression("0,10,20,30,40,50 * * * * ? ");
        job.setRunOnce(false);
        return job;
    }
}
