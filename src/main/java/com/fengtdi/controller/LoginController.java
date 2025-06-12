package com.fengtdi.controller;

import com.fengtdi.pojo.LoginInfo;
import com.fengtdi.pojo.Result;
import com.fengtdi.pojo.User;
import com.fengtdi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/login")
@RestController
public class LoginController {


    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping
    public Result login(@RequestBody User user){
        log.info("登录: {}", user);
        LoginInfo info =  userService.login(user);
        if (info != null){
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }
}

// controller -> service -> mapper