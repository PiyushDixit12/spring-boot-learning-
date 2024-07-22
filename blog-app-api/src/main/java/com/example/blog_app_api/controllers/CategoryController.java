package com.example.blog_app_api.controllers;

import com.example.blog_app_api.payloads.CategoryDto;
import com.example.blog_app_api.services.CategoryService;
import com.example.blog_app_api.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
@Tag(name = "Category Controller",description = "This is for managing Category in Blog Application")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>( categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable int id) {
        return  new ResponseEntity<>(categoryService.updateCategory(categoryDto,id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "get all Categories",description = "get all Categories Description")
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",description = "Success"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",description ="Unauthorized" ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",description = "Internal server error")
            }
    )
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
     List<CategoryDto> categories =categoryService.getAllCategories();
      return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int id) {
       return new ResponseEntity<>( categoryService.getCategoryById(id), HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
    }

}
