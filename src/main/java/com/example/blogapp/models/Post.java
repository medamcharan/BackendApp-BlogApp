package com.example.blogapp.models;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* import org.springframework.data.annotation.Id;
 import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

 @Document(collection = "posts")*/
 @Entity
@Table(name = "posts")
public class Post {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private boolean approved;

    //@DBRef
    // Replace @DBRef with @ManyToOne and @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
   
    
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Constructors
    public Post() {}

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.approved = false;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", approved=" + approved +
                ", user=" + user +
                '}';
    }
}