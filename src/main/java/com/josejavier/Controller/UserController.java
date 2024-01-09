package com.josejavier.Controller;

import com.josejavier.model.user;
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
    public ResponseEntity<List<user>> getAllUsers() {
        List<user> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<user> getUserById(@PathVariable("id") int id) {
        user user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<user> CreateUser(@RequestBody user user) {
        user end = service.CreateUser(user);
        return ResponseEntity.ok(end);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
