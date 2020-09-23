package com.ng.test;

import com.ng.dao.UserDao;
import com.ng.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;


public class UserDaoTest {

  //  @Autowired
//    @Qualifier("userDao")
//    @Resource
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        String source = "conf/applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(source);
        userDao = ctx.getBean("userDao", UserDao.class);
    }

    @Test
    public void ttttttt(){
        System.out.println(userDao);
    }

    @Test
    public void selectUser() {
        User user = userDao.selectUser(5);
        System.out.println(user);
    }

    @Test
    public void selectUsers() {
        List<User> users = userDao.selectUsers();
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void insertUser() {
    }
}