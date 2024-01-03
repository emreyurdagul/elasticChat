package com.example.elasticchat.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

@Data
@Document(indexName = "products") // replace with your actual index name
public class Product {

    @Id
    private String id; // Maps to _id

    // Assuming these fields are part of the Elasticsearch metadata and not usually part of the document mapping.
    // private String _index;
    // private Double _score;

    // Map the fields inside _source here
    @Field(type = FieldType.Nested)
    private List<Attribute> attributes;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Text)
    private String breadCrumbs;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Object)
    private CategoryPageData categoryPageData;

    @Field(type = FieldType.Keyword)
    private String delivery;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private List<String> features;

    @Field(type = FieldType.Boolean)
    private Boolean inStock;

    @Field(type = FieldType.Object)
    private Price listPrice;

    @Field(type = FieldType.Nested)
    private List<Attribute> manufacturerAttributes;

    @Field(type = FieldType.Object)
    private Price price;

    @Field(type = FieldType.Nested)
    private List<Attribute> productOverview;

    @Field(type = FieldType.Object)
    private Price shippingPrice;

    @Field(type = FieldType.Double)
    private Double stars;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String url;

    // Inner classes to represent complex fields
    @Data
    public static class Attribute {
        @Field(type = FieldType.Text)
        private String key;

        @Field(type = FieldType.Text)
        private String value;

        // getters and setters
    }

    @Data
    public static class CategoryPageData {
        @Field(type = FieldType.Text)
        private String categoryUrl;

        @Field(type = FieldType.Long)
        private Long productPosition;

        // getters and setters
    }

    @Data
    public static class Price {
        @Field(type = FieldType.Text)
        private String currency;

        @Field(type = FieldType.Float)
        private Float value;

        // getters and setters
    }

    // Getters and setters for all fields
}
