package com.datamodeling.DataModeling.repositories;

import com.datamodeling.DataModeling.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

