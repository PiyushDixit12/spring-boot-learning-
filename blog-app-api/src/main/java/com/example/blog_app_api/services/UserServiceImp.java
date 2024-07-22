package com.example.blog_app_api.services;

import com.example.blog_app_api.entities.User;
import com.example.blog_app_api.exceptions.ResourceNotFoundException;
import com.example.blog_app_api.payloads.UserDto;
import com.example.blog_app_api.repositries.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = Optional.ofNullable(userRepo.findByName(username).orElseThrow(() -> new ResourceNotFoundException("User", "userName not found " + username, 0)));
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found "+username));
    }


    //    MEthods
    @Override
    public UserDto createUser(UserDto user) {
        User newUser = modelMapper.map(user, User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User savedUser = userRepo.save(newUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user, int id) {
        Optional<User> optionalUser = Optional.ofNullable(this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)));
        if (optionalUser.isPresent()) {

            User updatedUser = optionalUser.get();
         if (user.getName()!= null && !user.getName().trim().isEmpty()) {
             updatedUser.setName(user.getName());
         }
         if (user.getEmail()!= null && !user.getEmail().trim().isEmpty()) {
             updatedUser.setEmail(user.getEmail());
         }
         if (user.getPassword()!= null && !user.getPassword().trim().isEmpty()) {
             updatedUser.setPassword(user.getPassword());
         }
         if (user.getAbout() != null && !user.getAbout().trim().isEmpty()) {
             updatedUser.setAbout(user.getAbout());
         }

            User updated = this.userRepo.save(updatedUser);
            return modelMapper.map(updated, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> optionalUser = Optional.ofNullable(this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id)));
        if (optionalUser.isPresent()) {
            return modelMapper.map(optionalUser.get(),UserDto.class);
        }
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(int id) {
        /*   first find that user present or not
         *   then delete user
         * */
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        this.userRepo.delete(user);
//        return userToDto(user);
    }


}
