package com.fengtdi.mapper;

import com.fengtdi.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM products")
    List<Product> selectAll();

    @Select("SELECT * FROM products LIMIT #{offset}, #{limit}")
    List<Product> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM products")
    int countAll();

    @Select("SELECT * FROM products WHERE id = #{id}")
    Product findById(Integer id);

    @Update("UPDATE products SET stock = stock - #{quantity} WHERE id = #{id} AND stock >= #{quantity}")
    int reduceStock(@Param("id") Integer id, @Param("quantity") int quantity);

    // 查询当前卖家发布的所有商品（假设 sellerId = userId）
    @Select("SELECT * FROM products WHERE seller_id = #{userId}")
    List<Product> selectBySellerId(@Param("userId") Integer userId);

    // 新增商品
    @Insert({
            "INSERT INTO products(name, price, image_url, description, seller_id, created_at)",
            "VALUES(#{name}, #{price}, #{imageUrl}, #{description}, #{sellerId}, NOW())"
    })
    void insert(Product product);

    // 更新商品状态
    @Update("UPDATE products SET status = #{status}, update_at = NOW() WHERE id = #{productId}")
    void updateStatus(@Param("productId") Integer productId, @Param("status") Integer status);
}