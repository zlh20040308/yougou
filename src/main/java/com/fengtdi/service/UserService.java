package com.fengtdi.service;

import com.fengtdi.pojo.LoginInfo;
import com.fengtdi.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void add(User user);

    LoginInfo login(User user);

    void register(User user);

    User findByUsername(String username);

    List<User> getUsersByRoleId(int i);

    boolean deleteUserById(Integer userId);
}

