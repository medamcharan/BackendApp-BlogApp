/*package com.example.blogapp.repositories;

import com.example.blogapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}*/
package com.example.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapp.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
