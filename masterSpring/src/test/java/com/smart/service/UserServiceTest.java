package com.smart.service;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Transactional
@ContextConfiguration({"classpath*:/smart-context.xml"})  // 启动Spring容器
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    private UserService userService;

    @Test
    public void getUserByUserName() {
        User test = new User();
        test.setUserName("admin");
        test.setPassword("123456");
        User user = userService.getByNameAndPassword(test);
        assertEquals("admin", user.getUserName());

        userService.loginSuccess(user);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
