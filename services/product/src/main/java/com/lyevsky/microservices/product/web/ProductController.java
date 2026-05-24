package com.lyevsky.microservices.product.web;


import com.lyevsky.microservices.product.service.ProductService;
import com.lyevsky.microservices.product.dto.ProductPurshaseRequest;
import com.lyevsky.microservices.product.dto.ProductPurshaseResponse;
import com.lyevsky.microservices.product.dto.ProductRequest;
import com.lyevsky.microservices.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurshaseResponse>> purshaseProducts(@RequestBody List<ProductPurshaseRequest> requests){
        return ResponseEntity.ok(productService.purshaseProducts(requests));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Long ProducId
    ){
        return ResponseEntity.ok(productService.findById(ProducId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

}
