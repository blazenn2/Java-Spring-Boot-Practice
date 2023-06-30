package com.example.demo.post;

public class Post {

    private Integer id;
    private Integer userId;
    private String postTitle;
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
