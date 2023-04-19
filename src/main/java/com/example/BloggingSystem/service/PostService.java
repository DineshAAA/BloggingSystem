package com.example.BloggingSystem.service;

import com.example.BloggingSystem.dao.PostRepository;
import com.example.BloggingSystem.entity.Post;
import com.example.BloggingSystem.util.PostValidation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class PostService {
    @Autowired
    PostValidation postValidation;

    @Autowired
    PostRepository postRepository;
    public int savePost(Post post) {
        return postRepository.save(post).getPostId();
    }

    public JSONArray getPost() {
        JSONArray response = new JSONArray();
        List<Post> postList = postRepository.findAll();
        for(Post post : postList){
            JSONObject json = postValidation.convertingPost(post);
            response.put(json);
        }
        return response;
    }

    public JSONArray getByID(String userId, String postId) {
        JSONArray response = new JSONArray();
        if(null!=postId && postRepository.findById(Integer.valueOf(postId)).isPresent()){
            Post post = postRepository.findById(Integer.valueOf(postId)).get();
            JSONObject json = postValidation.convertingPost(post);
            response.put(json);
        }
        return response;
    }

    public void putPost(String postId, Post updatedPost) {
        if(null!=postId && postRepository.findById(Integer.valueOf(postId)).isPresent()){
            Post olderPost = postRepository.findById(Integer.valueOf(postId)).get();
            olderPost.setPostName(updatedPost.getPostName());
            olderPost.setPostDescription(updatedPost.getPostDescription());
            olderPost.setUser(updatedPost.getUser());
            olderPost.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            postRepository.save(olderPost);
        }
    }

    public void delete(String postId) {
        if(null!=postId && postRepository.findById(Integer.valueOf(postId)).isPresent()){
            postRepository.deleteById(Integer.valueOf(postId));
        }
    }
}
