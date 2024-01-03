package com.example.elasticchat.service;

import com.example.elasticchat.model.Product;
import com.example.elasticchat.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getProducts(){
        return productRepository.findAll();
    }

    public List<String> getAllBreadCrumbs() {
        // Fetch all products (or use custom query methods as needed)
        Iterable<Product> products = productRepository.findAll();

        // Extract and return breadcrumbs from each product
        return StreamSupport.stream(products.spliterator(), false)
                .map(Product::getBreadCrumbs)  // assuming getBreadCrumbs is the getter method for breadcrumbs
                .collect(Collectors.toList());
    }
}
