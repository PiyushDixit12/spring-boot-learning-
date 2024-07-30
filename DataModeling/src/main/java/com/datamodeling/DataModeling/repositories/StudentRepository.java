package com.datamodeling.DataModeling.repositories;

import com.datamodeling.DataModeling.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}