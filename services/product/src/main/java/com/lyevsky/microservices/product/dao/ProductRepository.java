package com.lyevsky.microservices.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdInOrderByIdAsc(List<Long> ids);
}
