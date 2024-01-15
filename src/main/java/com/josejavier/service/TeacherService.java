package com.josejavier.service;

import com.josejavier.model.teacher;
import com.josejavier.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository repo;

    public List<teacher> getAllTeachers() {
        List<teacher> teachers = repo.findAll();
        return teachers;
    }

    public teacher getTeacherById(int id) {
        Optional<teacher> teacher = repo.findById(id);
        if(teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }

    public teacher CreateTeacher(teacher teacher) {
        teacher end;
        if(teacher.getId() != -1) {//update
            Optional<teacher> result = repo.findById(teacher.getId());
            if(result.isPresent()) {
                teacher u=result.get();
                u.setName(teacher.getName());
                u.setAge(teacher.getAge());
                end = repo.save(u);
            }else {
                throw new RuntimeException("Teacher not found with id: " + teacher.getId());
            }
        }else { //insert
            end = repo.save(teacher);
        }
        return end;
    }

    public void deleteTeacher(int id) {
        Optional<teacher> result = repo.findById(id);
        if(result.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }

}
