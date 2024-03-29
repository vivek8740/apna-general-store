package com.apnageneralstore.repository;

import com.apnageneralstore.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
