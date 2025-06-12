package com.fengtdi.service.impl;
import com.fengtdi.mapper.OrderMapper;
import com.fengtdi.mapper.ProductMapper;
import com.fengtdi.pojo.Order;
import com.fengtdi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 创建订单
     */
    public boolean createOrder(Order order) {
        return orderMapper.insert(order) > 0;
    }

    /**
     * 获取当前用户的订单列表
     */
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderMapper.selectByUserId(userId);
    }

    /**
     * 获取订单详情
     */
    public Order getOrderDetail(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

    /**
     * 更新订单状态
     */
    public boolean updateOrderStatus(String orderNo, Integer newStatus) {
        return orderMapper.updateStatus(orderNo, newStatus) > 0;
    }

    /**
     * 删除订单
     */
    public boolean deleteOrder(String orderNo) {
        return orderMapper.deleteByOrderNo(orderNo) > 0;
    }

    /**
     * 生成唯一订单号（示例）
     */
    public String generateOrderNo() {
        // 示例格式：20250506123456 + 随机数
        LocalDateTime now = LocalDateTime.now();
        String base = String.format("%04d%02d%02d%02d%02d%02d",
                now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                now.getHour(), now.getMinute(), now.getSecond());
        String random = String.format("%06d", (int)(Math.random() * 100000));
        return base + random;
    }

    @Override
    public String getOrderProductName(Integer productId) {
        return productMapper.findById(productId).getName();
    }


}
