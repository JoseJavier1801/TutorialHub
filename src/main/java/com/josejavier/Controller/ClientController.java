package com.josejavier.Controller;

import com.josejavier.model.Client;
import com.josejavier.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> getAllUsers() {
        List<Client> clients = service.getAllUsers();
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getUserById(@PathVariable("id") int id) {
        Client client = service.getUserById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> CreateUser(@RequestBody Client client) {
        Client end = service.CreateUser(client);
        return ResponseEntity.ok(end);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}