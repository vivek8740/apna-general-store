package com.apnageneralstore.repository;

import com.apnageneralstore.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String categoryName);
}
