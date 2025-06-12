package com.fengtdi.controller;

import com.fengtdi.pojo.Cart;
import com.fengtdi.pojo.CartVO;
import com.fengtdi.pojo.Result;
import com.fengtdi.service.CartService;
import com.fengtdi.service.ProductService;
import com.fengtdi.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     */
    @PostMapping
    public Result addToCart(@RequestBody Cart cart) {
        // 校验参数
        if (cart.getProductId() == null || cart.getProductId() <= 0) {
            return Result.error("商品ID无效");
        }

        if (cart.getQuantity() == null || cart.getQuantity() <= 0) {
            return Result.error("商品数量非法");
        }

        // ✅ 从 ThreadLocal 获取当前用户 ID
        Integer userId = UserContext.getCurrentUserId();
        if (userId == null) {
            return Result.error("用户未登录");
        }

        // 设置 cart 的 userId
        cart.setUserId(userId);

        boolean success = cartService.addToCart(cart);

        if (success) {
            return Result.success("加入购物车成功");
        } else {
            return Result.error("加入购物车失败");
        }
    }

    /**
     * 查询当前用户的购物车
     */
    @GetMapping
    public Result getCartList() {
        Integer userId = UserContext.getCurrentUserId();
        if (userId == null) {
            return Result.error("用户未登录");
        }

        List<CartVO> items = cartService.getCartItemsByUserId(userId);
        return Result.success(items);
    }

    /**
     * 删除某个商品
     */
    @DeleteMapping("/{productId}")
    public Result removeCartItem(@PathVariable("productId") Integer productId) {
        Integer userId = UserContext.getCurrentUserId();
        if (userId == null) {
            return Result.error("用户未登录");
        }

        boolean success = cartService.removeFromCart(userId, productId);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 清空购物车
     */
    @DeleteMapping
    public Result clearCart() {
        Integer userId = UserContext.getCurrentUserId();
        if (userId == null) {
            return Result.error("用户未登录");
        }

        boolean success = cartService.clearCart(userId);
        return success ? Result.success("清空购物车成功") : Result.error("清空失败");
    }

}
