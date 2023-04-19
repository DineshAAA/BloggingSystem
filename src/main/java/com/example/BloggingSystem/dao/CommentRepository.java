package com.example.BloggingSystem.dao;

import com.example.BloggingSystem.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Integer> {
}
