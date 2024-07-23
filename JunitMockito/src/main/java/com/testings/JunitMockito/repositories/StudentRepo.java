package com.testings.JunitMockito.repositories;


import com.testings.JunitMockito.entities.Student;
import org.springframework.data.repository.ListCrudRepository;

public interface StudentRepo extends ListCrudRepository<Student, Integer> {
}
