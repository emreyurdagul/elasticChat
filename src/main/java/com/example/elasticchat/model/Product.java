package com.example.elasticchat.model;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Document(indexName = "products")
public class Product {

    @Field(name = "product_id")
    private Long id;
}
