package com.datamodeling.DataModeling.repositories;

import com.datamodeling.DataModeling.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}