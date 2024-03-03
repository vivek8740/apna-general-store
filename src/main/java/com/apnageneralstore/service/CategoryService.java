package com.apnageneralstore.service;

import com.apnageneralstore.repository.ICategoryRepository;
import com.apnageneralstore.repository.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    ICategoryRepository categoryRepository;

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(Category category) {
        log.info("Data received {}",category);
        categoryRepository.save(category);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public void updateCategory(Integer categoryId, Category updatedCategory) {
        Category category= categoryRepository.findById(categoryId).get();
        category.setCategoryName(updatedCategory.getCategoryName());
        category.setDescription(updatedCategory.getDescription());
        category.setImageUrl(updatedCategory.getImageUrl());

        categoryRepository.save(category);
    }
}
