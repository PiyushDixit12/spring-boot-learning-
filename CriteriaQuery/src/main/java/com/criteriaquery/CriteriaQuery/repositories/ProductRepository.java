package com.criteriaquery.CriteriaQuery.repositories;

import com.criteriaquery.CriteriaQuery.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByNameContainingAndCategoryContaining(String name, String category);
}
