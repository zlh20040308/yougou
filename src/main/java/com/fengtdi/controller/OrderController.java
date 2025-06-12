package com.fengtdi.controller;

import com.fengtdi.pojo.Order;
import com.fengtdi.pojo.Product;
import com.fengtdi.pojo.Result;
import com.fengtdi.service.CartService;
import com.fengtdi.service.OrderService;
import com.fengtdi.service.ProductService;
import com.fengtdi.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    /**
     * 下单接口
     */
    @PostMapping
    public Result createOrder(@RequestBody Order order) {
        Integer userId = UserContext.getCurrentUserId();
        if (userId == null) {
            return Result.error("用户未登录");
        }
        log.info("{}", order);
        Product product = productService.findById(order.getProductId());
        order.setUserId(userId);
        order.setOrderNo(orderService.generateOrderNo());
        order.setProductName(product.getName());
        order.setStatus(1);
        order.setPrice(product.getPrice());
        order.setTotalPrice(order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        cartService.removeFromCart(userId, order.getProductId());
        return orderService.createOrder(order) ? Result.success() : Result.error("订单创建失败");

    }

    /**
     * 查询当前用户的所有订单
     */
    @GetMapping
    public Result listOrders() {
        Integer userId = UserContext.getCurrentUserId();
        if (userId == null) {
            return Result.error("用户未登录");
        }

        List<Order> orders = orderService.getOrdersByUserId(userId);
        return Result.success(orders);
    }


    /**
     * 查询当前用户的所有订单
     */
    @GetMapping("detail/{orderNo}")
    public Result getOrderDetail(@PathVariable("orderNo") String orderNo) {
        Order order = orderService.getOrderDetail(orderNo);
        if (order == null) {
            return Result.error("订单不存在");
        }
        return Result.success(order);
    }

    /**
     * 查看订单详情
     */
    @GetMapping("/{orderNo}")
    public Result viewOrder(@PathVariable("orderNo") String orderNo) {
        Order order = orderService.getOrderDetail(orderNo);
        if (order == null) {
            return Result.error("订单不存在");
        }
        return Result.success(order);
    }

    /**
     * 更新订单状态（如支付完成）
     */
    @PutMapping("/{orderNo}/status")
    public Result updateStatus(@PathVariable("orderNo") String orderNo,
                               @RequestParam("status") Integer newStatus) {
        boolean success = orderService.updateOrderStatus(orderNo, newStatus);
        return success ? Result.success("更新成功") : Result.error("更新失败");
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/{orderNo}")
    public Result deleteOrder(@PathVariable("orderNo") String orderNo) {
        boolean success = orderService.deleteOrder(orderNo);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}
