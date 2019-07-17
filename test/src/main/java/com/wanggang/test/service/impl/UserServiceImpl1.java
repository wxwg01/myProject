package com.wanggang.test.service.impl;

import com.wanggang.test.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wb-wg471966@alibaba-inc.com
 * @date : 2019/7/15
 */
@Service
public class UserServiceImpl1 implements UserService {
    @Override
    public String getUserName(String id) {
        return "a";
    }
}
