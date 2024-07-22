package com.example.blog_app_api.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //    @NotNull(message = "Please provide a User id.")
    private int id;

    @NotNull(message = "Please Provide a User name.")
    @Size(min = 4, message = "User name must be min 4 chars")
    private String name;

    @NotNull(message = "Please Provide a User email.")
    @Email(message = "Please Provide a valid email.")
    private String email;

    @NotNull(message = "Please Provide a User Password.")
    @Size(min = 8, max = 8, message = "Password must be min 8 and max 8 chars.")
    private String password;
    @NotNull(message = "Please provide a role")
    private String roles;

    @NotNull(message = "Please Provide a User about data.")
    private String about;

}
