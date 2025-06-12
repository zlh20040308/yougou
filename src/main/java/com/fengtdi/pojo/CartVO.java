package com.fengtdi.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 封装登录结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {
    private Integer productId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
