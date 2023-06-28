package com.example.demo.user;

import com.example.demo.exceptions.InvalidUserCreationException;
import com.example.demo.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService service;

    public UserResource(UserDaoService service) {
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
    public ResponseEntity createUser(@RequestBody User user) {
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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.noContent().build();
    }
}
