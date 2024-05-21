package com.example.shoppingmall.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product { // 도메인 객체
    private int id;
//    @JsonProperty("category_id")
    private int categoryId;
    private String name;
    private int price;
//    @JsonProperty("description_test")
    private String descriptionTest;
}
