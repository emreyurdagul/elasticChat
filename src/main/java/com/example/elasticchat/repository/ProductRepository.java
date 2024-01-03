package com.example.elasticchat.repository;

import com.example.elasticchat.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {


    public List<Product> findProductById(Long productId);




}
