package com.example.shoppingmall.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private int id;

    @JsonProperty("category_id")
    @NotBlank(message = "카테고리번호는 필수 입력 값입니다.")
    private int categoryId;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private int price;

    @NotBlank(message = "description은 필수 입력 값입니다.")
    private String description;

    public Product convertToEntity() {
        return new Product(categoryId, name, price, description);
    }
}
