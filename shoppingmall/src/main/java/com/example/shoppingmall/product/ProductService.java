package com.example.shoppingmall.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.shoppingmall.utils.ApiUtils.error;

@Service
@AllArgsConstructor // 필드로 생성자 코드 구현
public class ProductService {
    ProductRepository productRepository;
    ProductJPARepository productJPARepository;

    @Transactional
    public int registerProduct(Product product) {
        productJPARepository.save(product);
        return productJPARepository.findById(product.getId())
                .map(Product::getId)
                .orElseThrow(() -> new RuntimeException("Product Not Found" + product.getId()));
    }

    @Transactional
    public Product findProduct(int id) {
        return productJPARepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"+id));
        // Todo error 처리 다시 해야 할 것 같음 ..
    }

    @Transactional
    public List<Product> findProducts() {
        return productJPARepository.findAll();
    }

//    @Transactional
//    public List<Product> findProducts(int limit, int currentPage,int categoryId) {
//        return productJPARepository.findByCategoryId(limit, currentPage, categoryId);
//    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    public void deleteProducts(List<Integer> productIds) {
        productRepository.deleteProducts(productIds);
    }
}
