package com.datamodeling.DataModeling.repositories;

import com.datamodeling.DataModeling.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}