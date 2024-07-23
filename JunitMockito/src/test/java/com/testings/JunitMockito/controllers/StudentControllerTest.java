package com.testings.JunitMockito.controllers;

import com.testings.JunitMockito.entities.Student;
import com.testings.JunitMockito.services.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    static List<Student> students = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;


    @BeforeEach
    public void init() {

        students.add(new Student(1, "piyush"));
        students.add(new Student(2, "harsh"));
    }

    @Test
    public void findAll() throws Exception {
        String jsonResponse = """
                [
                    {
                        "id":1,
                        "name":"piyush"
                    },
                    {
                        "id":2,
                        "name":"harsh"
                    }
                ]
                """;

        Mockito.when(studentService.getAllStudents()).thenReturn(students);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/student"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));

        JSONAssert.assertEquals(jsonResponse, resultActions.andReturn().getResponse().getContentAsString(), false);
        System.out.println("Find all students successful");
    }

    @Test
    public void findById() throws Exception {
        String jsonResponse = """
                    {
                        "id":1,
                        "name":"piyush"
                    }
                """;

        Mockito.when(studentService.getStudent(1)).thenReturn(Optional.of(students.get(0)));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(jsonResponse));

        JSONAssert.assertEquals(jsonResponse, resultActions.andReturn().getResponse().getContentAsString(), false);
        System.out.println("Find by id students successful");
    }


    @Test
    public void updateStudent() throws Exception {
        Student updatedStudent = new Student(1, "updated student");
        String jsonResponse = """
                                
                {
                "id":1,
                "name":"updated student"
                }

                """;

        Mockito.when(studentService.updateStudent(Mockito.any(Student.class))).thenReturn(updatedStudent);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/student")
                        .contentType("application/json").content(jsonResponse))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));

        JSONAssert.assertEquals(jsonResponse, resultActions.andReturn().getResponse().getContentAsString(), false);
        System.out.println("Update student successfully");
    }

    @Test
    public void deleteStudent() throws Exception {
        Mockito.doNothing().when(studentService).deleteStudent(1);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println("Delete student successful");
    }

    @Test
    public void addStudent() throws Exception {
        String newStudentJson = """
                {
                    "id":3,
                    "name":"john"
                }
                """;

        Student newStudent = new Student(3, "john");
        Mockito.when(studentService.addStudent(Mockito.any(Student.class))).thenReturn(newStudent);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStudentJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(newStudentJson));

        JSONAssert.assertEquals(newStudentJson, resultActions.andReturn().getResponse().getContentAsString(), false);
        System.out.println("Add student successful");
    }

    @AfterEach
    void clean() {
        students.removeAll(students);
    }
}
