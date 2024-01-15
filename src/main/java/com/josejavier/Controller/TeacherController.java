package com.josejavier.Controller;



import com.josejavier.model.teacher;
import com.josejavier.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService service;

    @GetMapping
    public ResponseEntity<List<teacher>> getAllTeachers() {
        List<teacher> teachers=service.getAllTeachers();
        return  ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<teacher> getTeacherById(@PathVariable("id") int id) {
        teacher teacher=service.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<teacher> CreateTeacher(@RequestBody teacher teacher) {
        teacher end=service.CreateTeacher(teacher);
        return ResponseEntity.ok(end);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") int id) {
        service.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }
}
