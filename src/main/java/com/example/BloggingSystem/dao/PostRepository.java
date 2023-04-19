package com.example.BloggingSystem.dao;

import com.example.BloggingSystem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query(value = "Select * from post_table where post_id =:postId and status =1", nativeQuery = true)
    public List<Post> findByPostId(int postId);
}
