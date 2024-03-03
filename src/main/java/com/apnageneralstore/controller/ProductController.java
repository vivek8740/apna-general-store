package com.apnageneralstore.controller;

import com.apnageneralstore.common.ApiResponse;
import com.apnageneralstore.dto.ProductDto;
import com.apnageneralstore.repository.entity.Category;
import com.apnageneralstore.service.CategoryService;
import com.apnageneralstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing product-related operations.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    /**
     * Retrieves a list of all products.
     *
     * @return ResponseEntity containing a list of ProductDto objects and HTTP status OK if successful,
     *         or an empty list and HTTP status NOT FOUND if no products are found
     */
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct (@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(false, "Category is invalid."), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added."), HttpStatus.CREATED);
    }
}
