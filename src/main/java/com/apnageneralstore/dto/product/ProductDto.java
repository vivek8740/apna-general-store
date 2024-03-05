/**
 * DTO (Data Transfer Object) class representing a product.
 */
package com.apnageneralstore.dto.product;

import com.apnageneralstore.repository.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    /**
     * The name of the product.
     */
    private @NotNull String name;

    /**
     * The URL of the product's image.
     */
    private @NotNull String imageUrl;

    /**
     * The price of the product.
     */
    private @NotNull double price;

    /**
     * The description of the product.
     */
    private @NotNull String description;

    /**
     * The ID of the category to which the product belongs.
     */
    private @NotNull Integer categoryId;

    /**
     * Constructor to create a ProductDto from a Product entity.
     *
     * @param product The product entity from which to create the DTO.
     */
    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setImageUrl(product.getImageUrl());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
    }
}