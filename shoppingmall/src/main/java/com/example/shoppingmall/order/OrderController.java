package com.example.shoppingmall.order;

import com.example.shoppingmall.product.Product;
import com.example.shoppingmall.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderController {
    OrderService orderService;
    ProductService productService;

    @PostMapping("/orders")
    public void orderProduct(@RequestBody OrderDTO orderDTO) {
        Product orderedProduct =
                productService.findProduct(orderDTO.getProductId());

        // TODO : Service로 이사 갈거에오. DTO -> Entity
        Order requestOrder =  new Order(
                orderedProduct,
                orderDTO.getCount()
        ); // 객체 생성


//        requestOrder.setProduct(orderedProduct);
//        requestOrder.setCount(orderDTO.getCount());
        orderService.orderProduct(requestOrder);
    }

//    @GetMapping("/test")
//    public String test(){
//        return "test";
//    }
}
