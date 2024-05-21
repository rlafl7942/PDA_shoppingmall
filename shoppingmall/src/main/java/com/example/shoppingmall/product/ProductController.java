package com.example.shoppingmall.product;

import com.example.shoppingmall.utils.Validator;
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

@Slf4j
@RestController
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    // 상품 개별 등록
    @PostMapping("/products")
    public ResponseEntity registerProduct(@RequestBody Product product) {
        // * 유효성 검사 : name(영어), price(숫자)
        // Validator 통해서 유효성 검사
        if (Validator.isAlpha(product.getName())&&
                Validator.isNumber(product.getPrice())) {


//            System.out.println("/products : controller - " + product.getName());
            log.info(product.getName());
            Product savedProduct = productService.registerProduct(product);

            try{
//                System.out.println("res"+savedProduct.getName());
                log.info(savedProduct.getName());
            } catch(NullPointerException e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable(value = "id") int id) {
//        if (Validator.isNumber(id)) {
//            //Print(log) INFO 레벨로 로그 찍기 id 값의 타입 형태를 확인
//            log.info(id + "haha");
//            log.trace("id {}", "haha");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        Product resultProduct = productService.findProduct(id);
        if (resultProduct == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(resultProduct,HttpStatus.OK);
    }

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