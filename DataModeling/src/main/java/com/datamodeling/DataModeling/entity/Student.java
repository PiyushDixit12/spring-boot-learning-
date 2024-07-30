package com.datamodeling.DataModeling.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/**
 * MANY-TO-MANY RELATIONSHIP
 * */

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;
}