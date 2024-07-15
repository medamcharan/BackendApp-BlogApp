# Spring Boot with MongoDB Atlas Tutorial

This tutorial guides you through setting up a Spring Boot application to work with MongoDB Atlas, a fully managed cloud database service.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven
- MongoDB Atlas account
- IDE of your choice (e.g., IntelliJ IDEA, Eclipse)

## Step 1: Set up MongoDB Atlas

1. Create a MongoDB Atlas account if you don't have one.
2. Create a new cluster or use an existing one.
3. In the Atlas dashboard, go to "Database Access" and create a new database user:

   - Username: Choose a username (e.g., "springapp")
   - Password: Generate a secure password
   - Database User Privileges: Select "Atlas admin" for full access (adjust as needed for production)

4. In "Network Access", add your current IP address or use 0.0.0.0/0 to allow access from anywhere (not recommended for production).

5. In the cluster view, click "Connect" and choose "Connect your application". Copy the connection string.

## Step 2: Configure Spring Boot Application

1. Create a new Spring Boot project or use an existing one.

2. Add the MongoDB dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

3. In `src/main/resources/application.properties`, add:

```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/<database-name>?retryWrites=true&w=majority
```

Replace `<username>`, `<password>`, `<cluster-url>`, and `<database-name>` with your actual values.

### Connection String Explanation:

- `mongodb+srv://`: Indicates we're using MongoDB's DNS SRV connection method.
- `<username>:<password>`: Your Atlas database user credentials.
- `@<cluster-url>`: Your cluster's address (e.g., cluster0.abc123.mongodb.net).
- `/<database-name>`: The name of your database.
- `?retryWrites=true&w=majority`: Additional options for write concern and retry logic.

## Step 3: Create Model Classes

Create your model classes in the `model` package. For example:

```java
package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;

    // Constructors, getters, and setters
}
```

## Step 4: Create Repository Interfaces

Create repository interfaces in the `repository` package:

```java
package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
```

## Step 5: Create Service Classes

Create service classes in the `service` package:

```java
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Add other methods as needed
}
```

## Step 6: Create Controller Classes

Create controller classes in the `controller` package:

```java
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Add other endpoints as needed
}
```

## Step 7: Test the Application

1. Run your Spring Boot application.
2. Use a tool like Postman or curl to test your API endpoints:
   - GET `http://localhost:8080/api/users` to retrieve all users
   - POST `http://localhost:8080/api/users` with a JSON body to create a new user

## Troubleshooting

If you encounter connection issues:

1. Double-check your connection string in `application.properties`.
2. Ensure your IP address is whitelisted in MongoDB Atlas Network Access.
3. Verify that your database user has the correct permissions.
4. If using special characters in your password, ensure they are properly URL encoded.

## Security Considerations

- Never commit your `application.properties` file with sensitive information to version control.
- For production, use environment variables or a secure configuration management system to store your MongoDB URI.
- Limit database user permissions to only what's necessary for your application.
- Use IP whitelisting in production instead of allowing access from anywhere (0.0.0.0/0).

## Conclusion

This setup connects your Spring Boot application to MongoDB Atlas. You can now perform CRUD operations on your cloud-hosted MongoDB database. Remember to adjust security settings and optimize your application for production use.

---
