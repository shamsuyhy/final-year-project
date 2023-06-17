package com.backend.app.controller;

import com.backend.app.model.User;
import com.backend.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("{username}")
    public ResponseEntity<User> getUser(@PathVariable(name = "username") String username) {
        return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addUsers(@RequestBody List<User> users) {
        userService.addUsers(users);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("{username}")
    public ResponseEntity deleteUser(@PathVariable(name = "username") String username) {
        this.userService.deleteUser(username);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("assignUser/{username}")
    public ResponseEntity assignUser(@RequestBody List<Long> orderIds, @PathVariable(name = "username") String username) {
        this.userService.assignOrders(orderIds,username);
        return new ResponseEntity(HttpStatus.OK);
    }



}
