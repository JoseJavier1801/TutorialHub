package com.josejavier.Controller;



import com.josejavier.model.Teacher;
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
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> Teachers =service.getAllTeachers();
        return  ResponseEntity.ok(Teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") int id) {
        Teacher teacher=service.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<Teacher> CreateTeacher(@RequestBody Teacher teacher) {
        Teacher end=service.createOrUpdateTeacher(teacher);
        return ResponseEntity.ok(end);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") int id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        Teacher end=service.createOrUpdateTeacher(teacher);
        return ResponseEntity.ok(end);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") int id) {
        service.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }
}
