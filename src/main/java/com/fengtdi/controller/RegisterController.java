package com.fengtdi.controller;

import com.fengtdi.pojo.LoginInfo;
import com.fengtdi.pojo.Result;
import com.fengtdi.pojo.User;
import com.fengtdi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;


@Slf4j
@RequestMapping("/register")
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册接口（密码不加密，用于简单项目）
     */
    @PostMapping
    public Result register(@RequestBody User user) {
        // 1. 参数校验
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return Result.error("用户名或密码不能为空");
        }


        String username = user.getUsername().trim();
        String password = user.getPassword().trim();

        if (username.isEmpty() || password.isEmpty()) {
            return Result.error("用户名或密码不能为空");
        }

        // 2. 检查用户名是否已存在
        if (userService.findByUsername(username) != null) {
            return Result.error("该用户名已被注册");
        }

        // 3. 设置默认角色和创建时间
        user.setRoleId(1); // 默认角色为普通用户
        user.setCreatedAt(new Timestamp(System.currentTimeMillis())); // 设置当前时间

        // 4. 注册用户（密码以明文形式保存）
        userService.register(user);

        // 5. 返回成功结果
        return Result.success("注册成功");
    }
}

