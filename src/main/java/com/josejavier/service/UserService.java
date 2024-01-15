package com.josejavier.service;

import com.josejavier.model.User;
import com.josejavier.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public List<User> getAllUsers() {
        List<User> Users = repo.findAll();
        return Users;
    }
    public User getUserById(int id) {
        Optional<User> user = repo.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }


    public User CreateUser(User user) {
        User end;
        if(user.getId() != 0) {//update
            Optional<User> result = repo.findById(user.getId());
            if(result.isPresent()) {
                User u=result.get();
                u.setPhoto(user.getPhoto());
                u.setName(user.getName());
                u.setUsername(user.getUsername());
                u.setMail(user.getMail());
                u.setPassword(user.getPassword());
                u.setAge(user.getAge());
                u.setDate(user.getDate());
                u.setPhone(user.getPhone());
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
        Optional<User> result = repo.findById(id);
        if(result.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
