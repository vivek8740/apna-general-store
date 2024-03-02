package com.apnageneralstore.controller;

import com.apnageneralstore.dto.ProductDto;
import com.apnageneralstore.service.CategoryService;
import com.apnageneralstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
