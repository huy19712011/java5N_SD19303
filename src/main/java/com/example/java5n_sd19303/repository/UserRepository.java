package com.example.java5n_sd19303.repository;

import com.example.java5n_sd19303.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
