package com.example.shoppingmall.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductJPARepository
        extends JpaRepository<Product, Integer> {

//    Optional<Product> findById(int id);
//    List<Product> findAll();
//    @Query("SELECT p FROM Product p WHERE p.categoryId = :categoryId")
//    List<Product> findByCategoryId(int limit, int currentPage, int categoryId);
}
