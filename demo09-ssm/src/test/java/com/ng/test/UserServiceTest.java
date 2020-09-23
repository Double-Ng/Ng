package com.ng.test;

import com.ng.domain.User;
import com.ng.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

//    @Autowired
//    @Qualifier("userService")
    UserService userService;

   @Before
    public void init() {
        String config = "conf/applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        userService = ctx.getBean("userService", UserService.class);
    }

    @Test
    public void queryUser() {
        User user = userService.queryUser(2);
        System.out.println(user);
    }

    @Test
    public void queryUsers() {
        List<User> users = userService.queryUsers();
        users.forEach(s -> System.out.println(s));
    }

    @Test
    public void addUser() {
    }
}