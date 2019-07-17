package com.wanggang.test.utils;

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

    void insertAppQuartzSer(QuartzJobDetail appQuartz){
        System.out.println("yeah");
    };

    QuartzJobDetail selectAppQuartzByIdSer(int id){
        return getNewJob();
    }

    public void deleteAppQuartzByIdSer(Integer quartzId) {
    }

    public void updateAppQuartzSer(QuartzJobDetail appQuartz) {
    }

    List<QuartzJobDetail> getAll(){
        return new ArrayList<QuartzJobDetail>(){{
            this.add(getNewJob());
        }};
    }

    private QuartzJobDetail getNewJob(){
        QuartzJobDetail job = new QuartzJobDetail();
        job.setInvokeParam("com.wanggang.test");
        job.setJobName(DynamicJob.class.getName());
        job.setJobGroup("cdmp");
        job.setCronExpression("*/10 * * * * ?");
        return job;
    }
}
