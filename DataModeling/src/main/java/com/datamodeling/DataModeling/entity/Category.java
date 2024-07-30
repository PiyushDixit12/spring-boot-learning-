package com.datamodeling.DataModeling.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/**
 * ONE-TO-MANY RELATIONSHIP
 * */

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;
}

