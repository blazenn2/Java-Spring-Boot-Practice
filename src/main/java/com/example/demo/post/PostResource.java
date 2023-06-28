package com.example.demo.post;

import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.UserNotExistsException;
import com.example.demo.user.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostResource {
    @Autowired
    private PostDaoService service;
    @Autowired
    private UserDaoService userService;

    public PostResource(PostDaoService service, UserDaoService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(path = "/posts")
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getPostsOfUser(@PathVariable int id) {
        if (userService.findOne(id) == null) throw new UserNotExistsException("The user doesn't exists"); // We could replace it with UserNotFoundException but did this just for practising sake
        return service.getPostsOfUser(id);
    }

    @PostMapping(path = "/users/{id}/posts")
    public List<Post> addPostOfUser(@PathVariable int id,@RequestBody Post post) {
        if (post.getUserId() == null) post.setUserId(id);
        return service.addPostOfUser(post);
    }

    @GetMapping(path = "/users/{id}/posts/{post_id}")
    public Post getSingleUserPost(@PathVariable int id, @PathVariable int post_id) {
        if (userService.findOne(id) == null) throw new UserNotExistsException("The user doesn't exists");
        if (service.findASinglePost(post_id) == null) throw new PostNotFoundException("This post doesn't exists");
        Post post = service.getASinglePostOfUser(post_id, id);
        if (post == null) throw new PostNotFoundException("This post doesn't exists");
        else return post;
    }

}
