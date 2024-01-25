package com.josejavier.service;

import com.josejavier.model.Client;
import com.josejavier.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *  Client service class for CRUD operations on the database
 *  @author jose
 */
@Service
public class ClientService {

    @Autowired
    ClientRepository repo;

    /**
     *  Get all users from the database
     * @return
     */
    public List<Client> getAllClients() {
        List<Client> clients = repo.findAll();
        return clients;
    }

    /**
     *  Get a user from the database
     * @param id
     * @return
     */
    public Client getClientById(int id) {
        Optional<Client> user = repo.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    /**
     * Creates or updates a user in the database
     * @param client
     * @return
     */

    public Client CreateClient(Client client) {
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
            throw new RuntimeException("User created succesfully");
        }
        return end;

    }

    /**
     * Deletes a user from the database by id or throws an exception if not found
     *
     * @param id
     */

    public void deleteClient(int id) {
        Optional<Client> result = repo.findById(id);
        if(result.isPresent()) {
            repo.deleteById(id);
            throw new RuntimeException("User with id: " + id + " deleted succesfully ");
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
