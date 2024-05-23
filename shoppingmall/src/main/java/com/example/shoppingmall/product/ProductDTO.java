package com.example.shoppingmall.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private int id;

    @JsonProperty("category_id")
    @NotNull(message = "카테고리 번호는 필수 입력 값입니다.")
    @Positive(message = "카테고리 번호는 양의 정수여야 합니다.")
    private int categoryId;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    @Positive(message = "가격은 양의 정수여야 합니다.")
    private int price;

    @NotBlank(message = "상세 설명은 필수 입력 값입니다.")
    private String description;

    public Product convertToEntity() {
        return new Product(categoryId, name, price, description);
    }
}
