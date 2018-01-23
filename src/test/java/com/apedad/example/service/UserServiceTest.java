package com.apedad.example.service;

import com.alibaba.fastjson.JSON;
import com.apedad.example.annotation.TargetDataSource;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
    private static final Logger LOG = Logger.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void listAll() {
        LOG.info("user表数据：" + JSON.toJSONString(userService.listAll()));
    }

    @Test
    public void testRandom() {
        for (int i = 0; i < 100; i++) {
            LOG.info(RandomUtils.nextInt(0, 2));
        }
    }
}
