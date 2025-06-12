package com.fengtdi.service.impl;

import com.fengtdi.mapper.OrderMapper;
import com.fengtdi.mapper.ProductMapper;
import com.fengtdi.pojo.Order;
import com.fengtdi.pojo.Product;
import com.fengtdi.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    // 获取卖家自己的商品列表
    public List<Product> getProducts(Integer userId) {
        return productMapper.selectBySellerId(userId);
    }

    // 发布新商品
    public boolean addProduct(Product product, Integer sellerId) {
        product.setSellerId(sellerId);
        productMapper.insert(product);
        return true;
    }

    // 上下架商品
    public boolean updateProductStatus(Integer productId, Integer newStatus, Integer userId) {
        productMapper.updateStatus(productId, newStatus);
        return true;
    }

    // 获取卖家相关的订单
    public List<Order> getOrders(Integer userId) {
        return orderMapper.selectOrdersBySellerId(userId);
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

    @Override
    public boolean deliverOrder(Order order) {
        Order order1 = orderMapper.selectByOrderNo(order.getOrderNo());
        productMapper.reduceStock(order1.getProductId(), order1.getQuantity());
        orderMapper.deleteByOrderNo(order.getOrderNo());
        return true;
    }
}
