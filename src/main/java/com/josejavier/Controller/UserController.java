package com.josejavier.Controller;

import com.josejavier.model.User;
import com.josejavier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> Users = service.getAllUsers();
        return ResponseEntity.ok(Users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody User user) {
        User end = service.CreateUser(user);
        return ResponseEntity.ok(end);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        User end = service.CreateUser(user);
        return ResponseEntity.ok(end);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}