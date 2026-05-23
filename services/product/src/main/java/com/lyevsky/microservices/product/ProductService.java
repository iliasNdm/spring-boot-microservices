package com.lyevsky.microservices.product;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public Long createProduct(@Valid ProductRequest request) {
        return null;
    }

    public List<ProductPurshaseResponse> purshaseProducts(ProductPurshaseRequest request) {
        return null;
    }

    public ProductResponse findById(Long producId) {
        return null;
    }

    public List<ProductResponse> findAll() {
        return null;
    }
}
