package com.josejavier.service;

import com.josejavier.model.user;
import com.josejavier.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public List<user> getAllUsers() {
        List<user> users = repo.findAll();
        return users;
    }
    public user getUserById(int id) {
        Optional<user> user = repo.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }


    public user CreateUser(user user) {
        user end;
        if(user.getId() != -1) {//update
            Optional<user> result = repo.findById(user.getId());
            if(result.isPresent()) {
                user u=result.get();
                u.setName(user.getName());
                u.setAge(user.getAge());
                end = repo.save(u);
            }else {
                throw new RuntimeException("User not found with id: " + user.getId());
            }
        }else { //insert
            end = repo.save(user);
        }
        return end;

    }

    public void deleteUser(int id) {
        Optional<user> result = repo.findById(id);
        if(result.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }


}
