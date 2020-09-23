package com.ng.service;

import com.ng.domain.User;

import java.util.List;

public interface UserService {

    User queryUser(Integer id);
    List<User> queryUsers();
    User loginUser(User user);
    Integer registUser(User user);
    Boolean existUsername(String username);

}
