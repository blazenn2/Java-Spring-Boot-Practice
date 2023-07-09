package com.example.demo.post;

import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.UserNotExistsException;
import com.example.demo.user.UserDaoService;
import com.example.demo.user.User;
import com.example.demo.user.UserJdbcDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.shihyuho.jackson.databind.SerializeAllExcept;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
public class PostResource {
//    @Autowired
//    private PostDaoService service;
//    @Autowired
//    private UserDaoService userService;
//
//    public PostResource(PostDaoService service, UserDaoService userService) {
//        this.service = service;
//        this.userService = userService;
//    }

    @Autowired
    private final PostJdbcDaoService service;
    @Autowired
    private final UserJdbcDaoService userService;

    public PostResource(PostJdbcDaoService service, UserJdbcDaoService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(path = "/posts")
    public MappingJacksonValue getAllPosts() {
        List<Post> posts = service.getAllPosts();
        List<Map<String, Object>> newResult = new ArrayList<>();
        Iterator<Post> iterate = posts.iterator();
        while (iterate.hasNext()) {
            Post post = iterate.next();
            User user = userService.findOne(post.getUserId());
            Map<String, Object> postUserMap = new HashMap<>();
            postUserMap.put("postInformation", post);
            postUserMap.put("userInformation", user);
            newResult.add(postUserMap);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("posts", newResult);
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name", "dateOfBirth"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(response);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
//        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getPostsOfUser(@PathVariable int id) {
        if (userService.findOne(id) == null) throw new UserNotExistsException("The user doesn't exists"); // We could replace it with UserNotFoundException but did this just for practising sake
        return service.getPostsOfUser(id);
    }

    @PostMapping(path = "/users/{id}/posts")
    public List addPostOfUser(@PathVariable int id,@Valid @RequestBody Post post) {
        if (post.getUserId() == null) post.setUserId(id);
        List posts = service.addPostOfUser(post);
        return posts;
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
//        return ResponseEntity.created(location).build();
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
