package com.example.shoppingmall.order;

import com.example.shoppingmall.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@RequiredArgsConstructor
public class Order {
    int id;
    Product product; // Domain = Object
    int count;

    public Order(Product product, int count) {
        this.product = product;
        this.count = count;
    }
}
