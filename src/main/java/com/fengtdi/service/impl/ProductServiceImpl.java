package com.fengtdi.service.impl;

import com.fengtdi.mapper.ProductMapper;
import com.fengtdi.pojo.Product;
import com.fengtdi.pojo.Result;
import com.fengtdi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProducts(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return productMapper.selectByPage(offset, pageSize);

    }

    @Override
    public int countProducts() {
        return productMapper.countAll();
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }

    @Override
    public boolean reduceStock(Integer id, int quantity) {
        if (id == null || quantity <= 0) {
            throw new IllegalArgumentException("商品ID或数量无效");
        }
        int affectedRows = productMapper.reduceStock(id, quantity);
        return affectedRows > 0;
    }


}
