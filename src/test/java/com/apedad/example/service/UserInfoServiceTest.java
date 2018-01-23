package com.apedad.example.service;

import com.alibaba.fastjson.JSON;
import com.apedad.example.entity.User;
import com.apedad.example.entity.UserInfo;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserInfoServiceTest {
    private static final Logger LOG = Logger.getLogger(UserInfoServiceTest.class);

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;

    @Test
    public void listAll() {
        LOG.info("数据为：" + JSON.toJSONString(userInfoService.listAll()));
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        UserInfo userInfo = new UserInfo();
        userInfo.setName("test");
        userInfo.setEmail("test@test.com");

        LOG.info("user表写入结果：" + userService.insert(user));
        LOG.info("user_info写入结果：" + userInfoService.insert(userInfo));
    }

    @Transactional
    @Test
    public void testTx() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        UserInfo userInfo = new UserInfo();
        userInfo.setName("test");
        userInfo.setEmail("test@test.com");

        int res1 = userService.insert(user);
        int res = userInfoService.insert(userInfo);

//
//        LOG.info("user表写入结果：" + userService.insert(user));
//        LOG.info("user_info写入结果：" + userInfoService.insert(userInfo));
        if (res == 1) {
            throw new RuntimeException("测试回滚！");
        }
    }
}
