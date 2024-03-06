package com.apnageneralstore.repository;

import com.apnageneralstore.repository.entity.Cart;
import com.apnageneralstore.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
    List<Cart> deleteByUser(User user);
}
