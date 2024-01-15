package com.josejavier.service;

import com.josejavier.model.Teacher;
import com.josejavier.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                existingTeacher.setTitle(teacher.getTitle());
                existingTeacher.setBiography(teacher.getBiography());
                end = repo.save(existingTeacher);
            } else {
                throw new RuntimeException("Teacher not found with id: " + teacher.getId());
            }
        } else { // insert
            end = repo.save(teacher);
        }

        return end;
    }

    public void deleteTeacher(int id) {
        Optional<Teacher> result = repo.findById(id);
        if (result.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }
}

