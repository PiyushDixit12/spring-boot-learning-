package com.testings.JunitMockito.services;

import com.testings.JunitMockito.entities.Student;
import com.testings.JunitMockito.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public Optional<Student> getStudent(int id) {
        return studentRepo.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student updateStudent(Student student) {
        Optional<Student> st = studentRepo.findById(student.getId());
        if (st.isPresent()) {
            return studentRepo.save(student);
        }
        return null;
    }

    public  void  deleteStudent(int id){
        studentRepo.deleteById(id);
    }
}
