package com.ng.dao;

import com.ng.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao {

    User selectUser(Integer id);
    List<User> selectUsers();
    Integer insertUser(User user);
    User selectUserByName(String username);
    User selectUserByNameAndPasswd(String username, String password);

}
