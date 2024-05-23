package com.example.shoppingmall.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    private int id; // pk
    private int categoryId;
    private String name;
    private int price;
    private String description;


    public Product(int categoryId, String name, int price, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
