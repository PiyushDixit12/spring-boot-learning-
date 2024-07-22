package com.example.blog_app_api.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;

    @NotNull(message = "please provide a category title")
    @Size(min = 3,max = 20,message = "title must be min 3 and max 20 char")
    private String categoryTitle;

    @NotNull(message = "please provide a category description")
    @Size(min = 4,max = 130,message = "Description must be min 4 and max 130 char")
    private String categoryDescription;
}
