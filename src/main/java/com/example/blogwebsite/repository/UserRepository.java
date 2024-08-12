package com.example.blogwebsite.repository;

import com.example.blogwebsite.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
