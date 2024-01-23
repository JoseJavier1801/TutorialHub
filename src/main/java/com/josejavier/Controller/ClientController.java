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
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = service.getAllClients();
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") int id) {
        Client client = service.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> CreateClient(@RequestBody Client client) {
        Client end = service.CreateClient(client);
        return ResponseEntity.ok(end);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") int id) {
        service.deleteClient(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") int id, @RequestBody Client client) {
        client.setId(id);
        Client end = service.CreateClient(client);
        return ResponseEntity.ok(end);
    }

}