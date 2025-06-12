package com.fengtdi.service;

import com.fengtdi.pojo.Order;
import com.fengtdi.pojo.Product;

import java.util.List;

public interface SellerService {

    // 获取卖家自己的商品列表
    List<Product> getProducts(Integer userId);

    // 发布新商品
    boolean addProduct(Product product, Integer sellerId);

    // 上下架商品
    boolean updateProductStatus(Integer productId, Integer newStatus, Integer userId);

    // 获取卖家相关的订单
    List<Order> getOrders(Integer userId);

    Order getOrderByOrderNo(String orderNo);

    boolean deliverOrder(Order order);
}
