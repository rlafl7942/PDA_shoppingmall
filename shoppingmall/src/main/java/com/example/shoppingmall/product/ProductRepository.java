package com.example.shoppingmall.product;

import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    Map<Integer, Product> product_table = new HashMap<>();
    int id = 0;
    public Product save(Product product) {
        product.setId(id++);
        product_table.put(product.getId(), product);

        return product_table.get(id-1);
    }
    public Product findProduct(int id) {
        return product_table.get(id);
    }

    public List<Product> findProducts(int limit, int currentPage) {
        // map -> stream -> list
        // (currentPage - 1) * limit

        return product_table.values().stream().toList();
    }

    public List<Product> findProducts(int limit, int currentPage, int categoryId) {
        List<Product> resultProducts = new ArrayList<>();
        for (Product product : product_table.values()) {
            if (product.getCategoryId() == categoryId) {
                resultProducts.add(product);
            }
        }
        return resultProducts;
    }

    public void deleteProduct(int id) {
        product_table.remove(id);
    }

    public void deleteProducts(List<Integer> productIds) {
        for (int i=0; i<productIds.size(); i++) {
            product_table.remove(productIds.get(i));
        }
    }
}
