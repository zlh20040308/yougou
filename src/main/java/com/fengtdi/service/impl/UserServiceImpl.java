package com.fengtdi.service.impl;

import com.fengtdi.mapper.UserMapper;
import com.fengtdi.pojo.LoginInfo;
import com.fengtdi.pojo.User;
import com.fengtdi.service.UserService;
import com.fengtdi.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectAllUsers();
    }

    @Override
    public void add(User employee) {
        userMapper.insertUser(employee);
    }

    @Override
    public LoginInfo login(User user) {
        //1. 调用mapper接口, 根据用户名和密码查询员工信息
        User u = userMapper.selectByUsernameAndPassword(user);

        //2. 判断: 判断是否存在这个员工, 如果存在, 组装登录成功信息
        if(u != null){
            log.info("登录成功, 用户信息: {}", u);
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(u.getId(), u.getUsername(),u.getRoleId(), jwt);
        }

        //3. 不存在, 返回null
        return null;
    }

    @Override
    public void register(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> getUsersByRoleId(int i) {
        return userMapper.selectByRoleId(i);
    }

    @Override
    public boolean deleteUserById(Integer userId) {
        return userMapper.deleteById(userId) > 0;
    }
}
