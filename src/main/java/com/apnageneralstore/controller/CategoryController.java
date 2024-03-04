/**
 * Controller class for managing categories in the API.
 */
package com.apnageneralstore.controller;

import com.apnageneralstore.common.ApiResponse;
import com.apnageneralstore.repository.entity.Category;
import com.apnageneralstore.service.CategoryService;
import com.apnageneralstore.utils.Helper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * Get all categories.
     *
     * @return ResponseEntity with a list of categories and HTTP status OK.
     */
    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    /**
     * Create a new category.
     *
     * @param category The category to be created.
     * @return ResponseEntity with ApiResponse and HTTP status CREATED if successful.
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "Created the category."), HttpStatus.CREATED);
    }

    /**
     * Update an existing category.
     *
     * @param categoryId The ID of the category to be updated.
     * @param category   The updated category object.
     * @return ResponseEntity with ApiResponse and HTTP status OK if the category is updated,
     *         or with ApiResponse and HTTP status NOT_FOUND if the category does not exist.
     */
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Integer categoryId, @Valid @RequestBody Category category) {
        if (Helper.notNull(categoryService.readCategory(categoryId))) {
            categoryService.updateCategory(categoryId, category);
            return new ResponseEntity<>(new ApiResponse(true, "Category has been updated."), HttpStatus.OK);
        }
        categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(false, "Category does not exist."), HttpStatus.NOT_FOUND);
    }

}
