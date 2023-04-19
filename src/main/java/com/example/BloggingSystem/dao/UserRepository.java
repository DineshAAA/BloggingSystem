package com.example.BloggingSystem.dao;


import com.example.BloggingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "Select * from user_table where username = :userName", nativeQuery = true)
    public List<User> findUserByUserName(String userName);
}
