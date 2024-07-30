package com.datamodeling.DataModeling.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;


/**
 * MANY-TO-MANY RELATIONSHIP
 * */

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}