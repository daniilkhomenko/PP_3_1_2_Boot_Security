package com.example.springdemo.repository;

import com.example.springdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT p FROM User p JOIN FETCH p.roles WHERE p.username = (:username)")
    User findByUsername(String username);
}
