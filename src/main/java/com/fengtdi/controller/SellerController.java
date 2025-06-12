package com.fengtdi.controller;

import com.fengtdi.pojo.Order;
import com.fengtdi.pojo.Product;
import com.fengtdi.pojo.Result;
import com.fengtdi.service.SellerService;
import com.fengtdi.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/seller")
@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * 获取当前卖家的商品列表
     */
    @GetMapping("/products")
    public Result getProducts() {
        Integer sellerId = UserContext.getCurrentUserId();
        if (sellerId == null) {
            return Result.error("用户未登录");
        }

        List<Product> products = sellerService.getProducts(sellerId);
        return Result.success(products);
    }

    /**
     * 卖家发布新商品
     */
    @PostMapping("/product/add")
    public Result addProduct(@RequestBody Product product) {
        Integer sellerId = UserContext.getCurrentUserId();
        if (sellerId == null) {
            return Result.error("用户未登录");
        }
        sellerService.addProduct(product, sellerId);
        return Result.success();
    }

    @GetMapping("/order/{orderId}")
    public Result getOrderDetail(@PathVariable String orderId) {
        Integer sellerId = UserContext.getCurrentUserId();
        if (sellerId == null) {
            return Result.error("用户未登录");
        }

        Order order = sellerService.getOrderByOrderNo(orderId);
        if (order == null) {
            return Result.error("订单不存在或无权访问");
        }

        return Result.success(order);
    }

    /**
     * 获取当前卖家的所有订单
     */
    @GetMapping("/orders")
    public Result getOrders() {
        Integer sellerId = UserContext.getCurrentUserId();
        if (sellerId == null) {
            return Result.error("用户未登录");
        }

        List<Order> orders = sellerService.getOrders(sellerId);
        return Result.success(orders);
    }

    @PostMapping("/order/deliver")
    public Result deliverOrder(@RequestBody Order order) {
        Integer sellerId = UserContext.getCurrentUserId();
        if (sellerId == null) {
            return Result.error("用户未登录");
        }
        sellerService.deliverOrder(order);
        return Result.success();

    }
}
