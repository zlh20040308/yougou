package com.fengtdi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 封装登录结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Timestamp addedAt;
}
