package com.example.demo.post;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@JsonFilter("PostFilter")
public class Post {
    private Integer id;
    @NotNull
    private Integer userId;
    @Length(min = 3, message = "Length should be greater than 3")
    private String postTitle;
    @NotBlank(message = "Post message cannot be blank")
    private String postMessage;

    public Post(Integer id, Integer userId, String postTitle, String postMessage) {
        this.id = id;
        this.userId = userId;
        this.postTitle = postTitle;
        this.postMessage = postMessage;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", postTitle='" + postTitle + '\'' +
                ", postMessage='" + postMessage + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }
}
