package com.example.elasticchat.controller;


import com.example.elasticchat.model.Product;
import com.example.elasticchat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductService productService;


    @GetMapping("/all")
    public Iterable<Product> getProducts(){
        return  productService.getProducts();
    }


    @GetMapping("/breadCrumbs")
    public List<String> getBreadCrumbs(){
        return productService.getAllBreadCrumbs();
    }


}
