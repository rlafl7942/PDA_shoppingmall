package com.example.shoppingmall.product;

import com.example.shoppingmall.utils.ApiUtils;
import com.example.shoppingmall.utils.Validator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static com.example.shoppingmall.utils.ApiUtils.error;
import static com.example.shoppingmall.utils.ApiUtils.success;

@Slf4j
@RestController
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    // 상품 개별 등록
    @PostMapping("/products")
    public ApiUtils.ApiResult registerProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product requestProduct = productDTO.convertToEntity();
        int savedProduct = productService.registerProduct(requestProduct);
        return success(savedProduct);
    }

    // 상품 개별 조회
    @GetMapping("/products/{id}")
    public ApiUtils.ApiResult<Product> findProduct(@PathVariable(value = "id") int id) {
        Product resultProduct = productService.findProduct(id);
        if (resultProduct == null)
            System.out.println("hello");
//            return error("Not Found", HttpStatus.NOT_FOUND);
        return success(resultProduct);
    }

    // 상품 전체 조회
    @GetMapping("/products")
    public ResponseEntity<List<Product>> findProducts(@RequestParam ("limit") int limit,
                                                      @RequestParam("currentPage") int currentPage,
                                                      @RequestParam(value = "categoryId", required = false) Integer categoryId) {

        log.info("limit = {}", limit);
        log.info("currentPage = {}", currentPage);
        log.info("categoryId = {}", categoryId);

        if (categoryId == null) {
            List<Product> products = productService.findProducts(limit, currentPage);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            List<Product> products = productService.findProducts(limit, currentPage, categoryId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") int id) {
        if (!Validator.isNumber(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        productService.deleteProduct(id);
        Product product = productService.findProduct(id);

        if (product == null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/products/delete")
    public ResponseEntity deleteProducts(@RequestBody Map<String, List<Integer>> deleteRequest) {
        List<Integer> productIds = deleteRequest.get("productIds");

        if (productIds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.deleteProducts(productIds);
        return new ResponseEntity(HttpStatus.OK);
    }

}