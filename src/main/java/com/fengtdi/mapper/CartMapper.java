package com.fengtdi.mapper;
import com.fengtdi.pojo.Cart;
import com.fengtdi.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {


    /**
     * 添加购物车记录
     */
    @Insert("INSERT INTO carts(user_id, product_id, quantity, added_at) VALUES(#{userId}, #{productId}, #{quantity}, NOW())")
    int insert(Cart cart);

    /**
     * 根据用户ID查询购物车列表
     */
    @Select("SELECT * FROM carts WHERE user_id = #{userId}")
    List<Cart> selectByUserId(Integer userId);

    /**
     * 根据用户ID和商品ID查找购物车项
     */
    @Select("SELECT * FROM carts WHERE user_id = #{userId} AND product_id = #{productId}")
    Cart findByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    /**
     * 更新购物车中的商品数量
     */
    @Update("UPDATE carts SET quantity = #{quantity}, added_at = NOW() WHERE user_id = #{userId} AND product_id = #{productId}")
    int update(Cart cart);

    /**
     * 删除某个用户的某件商品
     */
    @Delete("DELETE FROM carts WHERE user_id = #{userId} AND product_id = #{productId}")
    int deleteByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    /**
     * 删除某个用户的所有商品
     */
    @Delete("DELETE FROM carts WHERE user_id = #{userId}")
    int deleteAllByUserId(Integer userId);

}
