package com.example.demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostResource {
    @Autowired
    private PostDaoService service;

    public PostResource(PostDaoService service) {
        this.service = service;
    }

    @GetMapping(path = "/posts")
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getPostsOfUser(@PathVariable int id) {
        return service.getPostsOfUser(id);
    }

    @PostMapping(path = "/users/{id}/posts")
    public List<Post> addPostOfUser(@PathVariable int id,@RequestBody Post post) {
        if (post.getUserId() == null) post.setUserId(id);
        return service.addPostOfUser(post);
    }

    @GetMapping(path = "/users/{id}/posts/{post_id}")
    public Post getSingleUserPost(@PathVariable int id, @PathVariable int post_id) {
        return service.getASinglePostOfUser(post_id, id);
    }

}
