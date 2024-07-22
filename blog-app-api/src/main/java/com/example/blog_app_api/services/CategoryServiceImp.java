package com.example.blog_app_api.services;

import com.example.blog_app_api.entities.Category;
import com.example.blog_app_api.exceptions.ResourceNotFoundException;
import com.example.blog_app_api.payloads.CategoryDto;
import com.example.blog_app_api.repositries.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        Optional<Category> category = Optional.ofNullable(categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id)));
        if (category.isPresent()) {
            return modelMapper.map(category.get(), CategoryDto.class);
        }
        return null;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryRepo.save(modelMapper.map(categoryDto,Category.class)), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto category, Integer categoryId) {
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId)));
        if (categoryOptional.isPresent()) {
            Category categoryToUpdate = categoryOptional.get();
            if (category.getCategoryTitle() != null && category.getCategoryTitle().trim() != "") {
                categoryToUpdate.setCategoryTitle(category.getCategoryTitle());
            }
            if (category.getCategoryDescription() != null && category.getCategoryDescription().trim() != "") {
                categoryToUpdate.setCategoryDescription(category.getCategoryDescription());
            }
            return modelMapper.map(categoryRepo.save(categoryToUpdate), CategoryDto.class);
        }
        return null;
    }

    @Override
    public void deleteCategory(int id) {
        Optional<Category> category = Optional.ofNullable(categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id)));
        if (category.isPresent()) {
            categoryRepo.deleteById(category.get().getId());
        }
    }
}
