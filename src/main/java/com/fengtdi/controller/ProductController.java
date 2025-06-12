package com.fengtdi.controller;

import com.fengtdi.pojo.Cart;
import com.fengtdi.pojo.Product;
import com.fengtdi.pojo.Result;
import com.fengtdi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Result getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int pageSize) {

        List<Product> items = productService.getProducts(page, pageSize);

        int total = productService.countProducts();

        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", items);


        return Result.success(data);
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Product product = productService.findById(id);
        return Result.success(product);
    }
}
