package com.fengtdi.controller;

import com.fengtdi.pojo.Result;
import com.fengtdi.pojo.User;
import com.fengtdi.service.UserService;
import com.fengtdi.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有卖家账号（roleId = 2）
     */
    @GetMapping("/sellers")
    public Result getAllSellers() {
        Integer adminId = UserContext.getCurrentUserId();
        if (adminId == null) {
            return Result.error("用户未登录");
        }
        List<User> sellers = userService.getUsersByRoleId(2); // roleId=2 表示卖家
        return Result.success(sellers);
    }

    /**
     * 添加卖家账号
     */
    @PostMapping("/seller/add")
    public Result addSeller(@RequestBody User user) {
        if (user == null || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return Result.error("用户名或密码不能为空");
        }
        user.setRoleId(2);
        userService.register(user);
        return Result.success();
    }

    /**
     * 删除卖家账号
     */
    @DeleteMapping("/seller/delete/{userId}")
    public Result deleteSeller(@PathVariable Integer userId) {
        Integer adminId = UserContext.getCurrentUserId();
        if (adminId == null) {
            return Result.error("请先登录");
        }

        boolean success = userService.deleteUserById(userId);
        if (success) {
            return Result.success(null);
        } else {
            return Result.error("删除失败");
        }
    }

    @GetMapping("/buyers")
    public Result getAllBuyers() {
        List<User> buyers = userService.getUsersByRoleId(1);
        return Result.success(buyers);
    }

    @DeleteMapping("/buyer/delete/{userId}")
    public Result deletBuyer(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return Result.success();
    }
}
