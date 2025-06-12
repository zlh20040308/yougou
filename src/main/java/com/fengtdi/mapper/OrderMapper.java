package com.fengtdi.mapper;


import com.fengtdi.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 新增订单
     */
    @Insert({
            "INSERT INTO orders(order_no, user_id, product_id, product_name, price, quantity, total_price, status, created_at, updated_at, receiver_address, receiver_name, receiver_phone)",
            "VALUES(#{orderNo}, #{userId}, #{productId}, #{productName}, #{price}, #{quantity}, #{totalPrice}, #{status}, NOW(), NOW(), #{receiverAddress}, #{receiverName}, #{receiverPhone})"
    })
    int insert(Order order);

    /**
     * 根据用户ID查询所有订单
     */
    @Select("SELECT * FROM orders WHERE user_id = #{userId}")
    List<Order> selectByUserId(Integer userId);

    /**
     * 根据订单编号查询订单详情
     */
    @Select("SELECT * FROM orders WHERE order_no = #{orderNo}")
    Order selectByOrderNo(String orderNo);

    /**
     * 更新订单状态（例如支付后）
     */
    @Update("UPDATE orders SET status = #{status}, updated_at = NOW() WHERE order_no = #{orderNo}")
    int updateStatus(@Param("orderNo") String orderNo, @Param("status") Integer status);

    /**
     * 删除订单
     */
    @Delete("DELETE FROM orders WHERE order_no = #{orderNo}")
    int deleteByOrderNo(String orderNo);

    @Select({
            "SELECT o.* FROM orders o",
            "JOIN products p ON o.product_id = p.id",
            "WHERE p.seller_id = #{sellerId}"  // 假设商品表有seller_id字段
    })
    List<Order> selectOrdersBySellerId(@Param("sellerId") Integer sellerId);
}