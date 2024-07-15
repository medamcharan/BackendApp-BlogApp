/*package com.example.blogapp.repositories;

import com.example.blogapp.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}*/
package com.example.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapp.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
