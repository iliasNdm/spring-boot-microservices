package com.lyevsky.microservices.product.service;

import com.lyevsky.microservices.product.dao.ProductRepository;
import com.lyevsky.microservices.product.dto.ProductPurshaseRequest;
import com.lyevsky.microservices.product.dto.ProductPurshaseResponse;
import com.lyevsky.microservices.product.dto.ProductRequest;
import com.lyevsky.microservices.product.dto.ProductResponse;
import com.lyevsky.microservices.product.exceptions.ProductNotFoundException;
import com.lyevsky.microservices.product.exceptions.ProductPurshaseException;
import com.lyevsky.microservices.product.mappers.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Long createProduct(@Valid ProductRequest request) {
        var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurshaseResponse> purshaseProducts(List<ProductPurshaseRequest> requests) {
        var productIds = requests.stream()
                .map(ProductPurshaseRequest::productId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderByIdAsc(productIds);

        if (storedProducts.size() != productIds.size()) {
            throw new ProductNotFoundException("One or more products were not found");
        }

        var sortedRequests = requests.stream()
                .sorted(Comparator.comparing(ProductPurshaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurshaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var purchaseRequest = sortedRequests.get(i);

            if (product.getAvailableQuantity() < purchaseRequest.quantity()) {
                throw new ProductPurshaseException(
                        "Insufficient stock for product with id: " + product.getId()
                );
            }

            product.setAvailableQuantity(product.getAvailableQuantity() - purchaseRequest.quantity());
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurshaseResponse(product, purchaseRequest.quantity()));
        }

        return purchasedProducts;
    }

    public ProductResponse findById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product with id " + productId + " not found"
                ));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
