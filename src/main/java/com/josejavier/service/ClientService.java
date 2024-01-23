package com.josejavier.service;

import com.josejavier.model.Client;
import com.josejavier.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repo;

    public List<Client> getAllUsers() {
        List<Client> clients = repo.findAll();
        return clients;
    }
    public Client getUserById(int id) {
        Optional<Client> user = repo.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }


    public Client CreateUser(Client client) {
        Client end;
        if(client.getId() != 0) {//update
            Optional<Client> result = repo.findById(client.getId());
            if(result.isPresent()) {
                Client u=result.get();
                u.setPhoto(client.getPhoto());
                u.setName(client.getName());
                u.setUsername(client.getUsername());
                u.setMail(client.getMail());
                u.setPassword(client.getPassword());
                u.setDate(client.getDate());
                u.setPhone(client.getPhone());
                end = repo.save(u);
            }else {
                throw new RuntimeException("User not found with id: " + client.getId());
            }
        }else { //insert
            end = repo.save(client);
        }
        return end;

    }

    public void deleteUser(int id) {
        Optional<Client> result = repo.findById(id);
        if(result.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
