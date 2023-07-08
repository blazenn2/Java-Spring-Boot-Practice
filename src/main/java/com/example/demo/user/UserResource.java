package com.example.demo.user;

import com.example.demo.exceptions.InvalidUserCreationException;
import com.example.demo.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Response{

}

@RestController
public class UserResource {
//    @Autowired
//    private UserDaoService services;
//
//    public UserResource(UserDaoService service) {
//        this.service = service;
//    }



    private UserJdbcDaoService service;

    public UserResource(UserJdbcDaoService service) {
        this.service = service;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping(path="/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) throw new UserNotFoundException("id-" + id);
        else return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        if (user.getName() == null || user.getName().length() == 0) throw new InvalidUserCreationException("Invalid request, add a proper key-value pair of name");
        if (user.getDateOfBirth() == null) throw new InvalidUserCreationException("Invalid request, add a proper key-value pair of date");
        User newUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        User user = service.deleteUser(id);
        if (user == null) throw new UserNotFoundException("id-" + id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Success");
        response.put("message", "User " + user.getName() + " got deleted successfully!");
        return ResponseEntity.status(200).body(response);

//        Below is the response code of the exercise
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
//        return ResponseEntity.status(200).body("User deleted succesfully");
    }
}
