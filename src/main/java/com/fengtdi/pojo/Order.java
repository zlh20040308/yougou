package com.fengtdi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private String orderNo;          // 唯一订单号
    private Integer userId;
    private Integer productId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Integer status;
    private String receiverAddress;
    private String receiverPhone;
    private String receiverName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
