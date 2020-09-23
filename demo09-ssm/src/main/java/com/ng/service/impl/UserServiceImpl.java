package com.ng.service.impl;

import com.ng.dao.UserDao;
import com.ng.domain.User;
import com.ng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User queryUser(Integer id) {
        User user = userDao.selectUser(id);
        if (user == null) {
            System.out.println("该id不存在，请重新输入！");
        }
        return user;
    }

    @Override
    public List<User> queryUsers() {
        return userDao.selectUsers();
    }

    @Override
    public User loginUser(User user) {
//        user = userDao.selectUserByNameAndPasswd(user.getUsername(), user.getPassword());
        User selectUser = userDao.selectUserByName(user.getUsername());
        if (selectUser != null && selectUser.getPassword().equals(user.getPassword())){
            return selectUser;
        }
        return null;
    }

    @Override
    public Integer registUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public Boolean existUsername(String username) {
        if (userDao.selectUserByName(username) != null) {
                return true;
        } else {
            return false;
        }
    }
}
