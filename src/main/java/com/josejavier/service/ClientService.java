package com.josejavier.service;

import com.josejavier.model.Client;
import com.josejavier.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public Client createClient(Client client) {
        // Si el cliente tiene un ID diferente de 0, significa que es una actualización
        if (client.getId() != 0) {
            Optional<Client> result = repo.findById(client.getId());
            if (result.isPresent()) {
                Client u = result.get();
                // Actualizar los campos del cliente con los nuevos valores
                u.setPhoto(client.getPhoto());
                u.setName(client.getName());
                u.setUsername(client.getUsername());
                u.setMail(client.getMail());
                // Hashear la contraseña antes de guardarla
                String hashedPassword = hashPassword(client.getPassword());
                u.setPassword(hashedPassword);
                u.setDate(client.getDate());
                u.setPhone(client.getPhone());
                // Guardar el cliente actualizado en la base de datos
                return repo.save(u);
            } else {
                throw new RuntimeException("User not found with id: " + client.getId());
            }
        } else { // Si el cliente tiene ID igual a 0, significa que es un nuevo cliente a insertar
            // Hashear la contraseña antes de guardarla
            String hashedPassword = hashPassword(client.getPassword());
            client.setPassword(hashedPassword);
            // Guardar el nuevo cliente en la base de datos
            return repo.save(client);
        }
    }

    // Método para hashear la contraseña
    private String hashPassword(String password) {
        String hash=org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
        System.out.println(hash);
        return  hash;
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
    public Client findByUsernameAndPassword(String username, String password) {
    System.out.println(username);
    System.out.println(password);
        return repo.findbyUsernameAndPassword(username, password);
    }
    public Client findById(int id) {
        Optional<Client> result = repo.findById(id);
        return result.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    public Client getClientByUsername(String username) {
        return repo.findByUsername(username);
    }


}
