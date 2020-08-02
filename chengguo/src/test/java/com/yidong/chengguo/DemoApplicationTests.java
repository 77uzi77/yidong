package com.yidong.chengguo;

import com.yidong.chengguo.dao.IUserDao;
import com.yidong.chengguo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private IUserDao userDao;

    @Test
    void contextLoads() {

            User user = new User();
            user.setUsername("1");
            user.setCode(UUID.randomUUID().toString().replace("-",""));
            user.setPassword("1");
            user.setEmail("1");
            user.setIcon("1");

            userDao.saveUser(user);

    }

}
