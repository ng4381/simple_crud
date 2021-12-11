package com.noname.simple_crud.controller;

import com.noname.simple_crud.model.User;
import com.noname.simple_crud.repository.UserRepository;
import com.noname.simple_crud.service.UserService;
import com.noname.simple_crud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<User>> getUsersById(@PathVariable long id) {

        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            return new ResponseEntity(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userService.create(user), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        User userData = userService.updateUser(id, user);
        return new ResponseEntity(userData, (userData == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
