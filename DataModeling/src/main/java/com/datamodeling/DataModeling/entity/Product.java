package com.datamodeling.DataModeling.entity;

import lombok.Data;
import jakarta.persistence.*;

/**
 * ONE-TO-MANY RELATIONSHIP
 * */

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}