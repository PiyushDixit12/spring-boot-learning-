package com.example.blog_app_api.services;

import com.example.blog_app_api.entities.Category;
import com.example.blog_app_api.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> getAllCategories();
    public CategoryDto getCategoryById(int id);
    public CategoryDto addCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto category,Integer categoryId);
    public void deleteCategory(int id);

}
