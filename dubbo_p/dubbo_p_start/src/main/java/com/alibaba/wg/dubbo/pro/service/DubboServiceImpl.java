package com.alibaba.wg.dubbo.pro.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.wg.dubbo.pro.client.DubboService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/5/9
 */
@Service(interfaceClass = DubboService.class)
@Component("dubboServiceImpl")
public class DubboServiceImpl implements DubboService {
    @Override
    public List<String> getList(String name) {
        return Lists.newArrayList("1","2","3","4","5");
    }
}
