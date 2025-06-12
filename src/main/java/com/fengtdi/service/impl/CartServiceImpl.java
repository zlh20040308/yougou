package com.fengtdi.service.impl;


import com.fengtdi.mapper.CartMapper;
import com.fengtdi.mapper.ProductMapper;
import com.fengtdi.pojo.Cart;
import com.fengtdi.pojo.CartVO;
import com.fengtdi.pojo.Product;
import com.fengtdi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    public boolean addToCart(Cart cart) {
        Cart existing = cartMapper.findByUserIdAndProductId(cart.getUserId(), cart.getProductId());

        if (existing != null) {
            // 已存在，更新数量
            existing.setQuantity(existing.getQuantity() + cart.getQuantity());
            return cartMapper.update(existing) > 0;
        } else {
            // 不存在，直接新增
            return cartMapper.insert(cart) > 0;
        }
    }

    public List<CartVO> getCartItemsByUserId(Integer userId) {
        List<Cart> carts = cartMapper.selectByUserId(userId);
        return carts.stream().map(cart -> {
            Product product = productMapper.findById(cart.getProductId());

            CartVO vo = new CartVO();
            vo.setProductId(cart.getProductId());
            vo.setQuantity(cart.getQuantity());
            vo.setPrice(product != null ? product.getPrice() : BigDecimal.ZERO);
            vo.setProductName(product != null ? product.getName() : "未知商品");
            return vo;
        }).collect(Collectors.toList());
    }

    public boolean removeFromCart(Integer userId, Integer productId) {
        return cartMapper.deleteByUserIdAndProductId(userId, productId) > 0;
    }

    public boolean clearCart(Integer userId) {
        return cartMapper.deleteAllByUserId(userId) > 0;
    }
}
