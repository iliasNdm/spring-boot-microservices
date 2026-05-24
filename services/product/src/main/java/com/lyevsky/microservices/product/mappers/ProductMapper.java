package com.lyevsky.microservices.product.mappers;

import com.lyevsky.microservices.product.dao.Category;
import com.lyevsky.microservices.product.dao.Product;
import com.lyevsky.microservices.product.dto.ProductPurshaseResponse;
import com.lyevsky.microservices.product.dto.ProductRequest;
import com.lyevsky.microservices.product.dto.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(Category.builder()
                        .id(request.categoryId().longValue())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId().intValue(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId().intValue(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurshaseResponse toProductPurshaseResponse(Product product, double quantity) {
        return new ProductPurshaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                quantity,
                product.getPrice()
        );
    }
}
