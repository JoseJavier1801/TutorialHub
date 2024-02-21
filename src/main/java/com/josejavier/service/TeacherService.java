package com.josejavier.service;

import com.josejavier.model.Teacher;
import com.josejavier.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repo;

    public List<Teacher> getAllTeachers() {
        return repo.findAll();
    }

    public Teacher getTeacherById(int id) {

        Optional<Teacher> teacher = repo.findById(id);
        System.out.println("Buscando profesor con ID: " + id);
        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }

    public Teacher createOrUpdateTeacher(Teacher teacher) {
        Teacher end;

        if (teacher.getId() != 0) { // update
            Optional<Teacher> result = repo.findById(teacher.getId());
            if (result.isPresent()) {
                Teacher existingTeacher = result.get();
                existingTeacher.setName(teacher.getName());
                existingTeacher.setDate(teacher.getDate());
                existingTeacher.setUsername(teacher.getUsername());
                existingTeacher.setMail(teacher.getMail());
                existingTeacher.setPhone(teacher.getPhone());
                existingTeacher.setPhoto(teacher.getPhoto());
                existingTeacher.setTitle(teacher.getTitle());
                existingTeacher.setBiography(teacher.getBiography());

                // Hashear la contraseña antes de guardarla
                String hashedPassword = hashPassword(teacher.getPassword());
                existingTeacher.setPassword(hashedPassword);

                end = repo.save(existingTeacher);
            } else {
                throw new RuntimeException("Teacher not found with id: " + teacher.getId());
            }
        } else { // insert
            // Hashear la contraseña antes de guardarla
            String hashedPassword = hashPassword(teacher.getPassword());
            teacher.setPassword(hashedPassword);

            end = repo.save(teacher);
        }

        return end;
    }

    // Método para hashear la contraseña
    private String hashPassword(String password) {
        String hash=org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
        System.out.println(hash);
        return  hash;
    }
    public void deleteTeacher(int id) {
        Optional<Teacher> result = repo.findById(id);
        if (result.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }
    public Teacher findByUsernameAndPassword(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }



}

