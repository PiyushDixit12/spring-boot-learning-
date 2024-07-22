package com.example.blog_app_api.controllers;

import com.example.blog_app_api.config.AppConstants;
import com.example.blog_app_api.entities.AuthRequest;
import com.example.blog_app_api.payloads.UserDto;
import com.example.blog_app_api.services.JwtService;
import com.example.blog_app_api.services.UserService;
import com.example.blog_app_api.services.UserServiceImp;
import com.example.blog_app_api.utils.ApiResponse;
import com.example.blog_app_api.utils.BlackList;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
@Tag(name = "User Controller",description = "This is for managing user in Blog Application")
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    BlackList blackList;

    @Autowired
    JwtService jwtService;

    @PostMapping(path = "/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public String addUser(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('"+ AppConstants.USER_ROLE +"') or hasAuthority('"+ AppConstants.ADMIN_ROLE +"')")

    public String logoutUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token= null;
        if(authHeader !=null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
        }
        blackList.blacKListToken(token);
        return "You have successfully logged out !!";
    }
    @GetMapping
    @PreAuthorize("hasAuthority('"+ AppConstants.ADMIN_ROLE +"')")
    public ResponseEntity<List<UserDto>> getUsers() {
        return   new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        return   new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user) {
        return  new ResponseEntity<>(userService.updateUser(user, user.getId()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return  new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);

    }
}
