package com.josejavier.Controller;

import com.josejavier.model.Client;
import com.josejavier.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        System.out.println(id);
        Client client = service.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> CreateClient(@RequestBody Client client) {
        Client end = service.createClient(client);
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
        Client end = service.createClient(client);
        return ResponseEntity.ok(end);
    }
    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestBody Client loginData) {
        String username = loginData.getUsername();
        String password = loginData.getPassword();
        System.out.println(password);
        System.out.println(username);

        // Hashear la contraseña proporcionada
        String hashedPassword = hashPassword(password);

        // Llama al método del servicio con la contraseña hasheada
        Client client = service.findByUsernameAndPassword(username, hashedPassword);

        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Método para hashear la contraseña
    private String hashPassword(String password) {
        String hash=org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
        System.out.println(hash);
        return  hash;
    }




}