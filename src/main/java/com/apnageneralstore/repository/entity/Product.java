package com.apnageneralstore.repository.entity;

import com.apnageneralstore.dto.product.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotNull String name;
    private @NotNull String imageUrl;
    private @NotNull double price;
    private @NotNull String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    public Product(ProductDto productDto, Category category) {
        this.name = productDto.getName();
        this.imageUrl= productDto.getImageUrl();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.category = category;
    }

}
