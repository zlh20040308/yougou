package com.fengtdi.service;

import com.fengtdi.pojo.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    /**
     * 创建订单
     */
    boolean createOrder(Order order);

    /**
     * 获取当前用户的订单列表
     */
    List<Order> getOrdersByUserId(Integer userId);

    /**
     * 获取订单详情
     */
    Order getOrderDetail(String orderNo);

    /**
     * 更新订单状态
     */
    boolean updateOrderStatus(String orderNo, Integer newStatus);

    /**
     * 删除订单
     */
    boolean deleteOrder(String orderNo);

    /**
     * 生成唯一订单号（示例）
     */
    String generateOrderNo();

    String getOrderProductName(Integer productId);
}
