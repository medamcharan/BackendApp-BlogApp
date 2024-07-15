package com.example.blogapp.services;

import java.util.List;

import com.example.blogapp.models.Post;
import com.example.blogapp.models.User;
import com.example.blogapp.repositories.PostRepository;
import com.example.blogapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(Post post) {
        if (post.getUser() != null && post.getUser().getId() != null) {
            User user = userRepository.findById(post.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            post.setUser(user);
        }
        return postRepository.save(post);
    }

    public void approvePost(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setApproved(true);
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Other methods for post management

    public Post getPostById(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post updatePost(String postId, Post updatedPost) {
        Post post = getPostById(postId);
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        return postRepository.save(post);
    }

    public void deletePost(String postId) {
        Post post = getPostById(postId);
        postRepository.delete(post);
    }
}