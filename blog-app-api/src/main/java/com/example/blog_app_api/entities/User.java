package com.example.blog_app_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name", nullable = false,length = 50)
    private String name;

    @Column(name = "email",unique = true)
    private String email;

    @Column(length = 255,nullable = false)
    private String password;

    private  String roles;

    @Column(nullable = false,length = 250)
    private  String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     private List<Post> posts = new ArrayList<>();

//    @OneToMany
//    private List<Comment> comments = new ArrayList<>();

}
