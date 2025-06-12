package com.fengtdi.service;

import com.fengtdi.pojo.Cart;
import com.fengtdi.pojo.CartVO;

import java.util.List;

public interface CartService {
    boolean addToCart(Cart cart);

     List<CartVO> getCartItemsByUserId(Integer userId);

     boolean removeFromCart(Integer userId, Integer productId);

     boolean clearCart(Integer userId);

}
