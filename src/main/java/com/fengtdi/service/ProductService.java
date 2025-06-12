package com.fengtdi.service;

import com.fengtdi.pojo.Product;
import com.fengtdi.pojo.Result;

import java.util.List;


public interface ProductService {
    List<Product> getProducts(int page, int pageSize);

    int countProducts();

    Product findById(Integer id);

    boolean reduceStock(Integer id, int quantity);
}
