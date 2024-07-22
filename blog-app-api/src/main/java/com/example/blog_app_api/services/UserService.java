package com.example.blog_app_api.services;


import com.example.blog_app_api.payloads.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, int id);

    UserDto getUserById(int id);

    List<UserDto> getAllUsers();

    void deleteUserById(int id);


}
