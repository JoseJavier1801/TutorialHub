package com.josejavier.Controller;

import com.josejavier.model.Classroom;
import com.josejavier.model.Teacher;
import com.josejavier.service.ClassroomService;
import com.josejavier.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    ClassroomService service;


    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        // Verificar el contenido del objeto classroom
       /* System.out.println("Contenido del objeto classroom: " + classroom);

        // Verificar si el profesor existe antes de guardar el aula
        Teacher existingTeacher = teacherService.getTeacherById(classroom.getTeacher().getId());

        if (existingTeacher == null) {
            // El profesor no existe, devuelve una respuesta BadRequest
            return ResponseEntity.badRequest().build();
        }

        // Asignar el profesor existente al aula antes de guardarlo
        classroom.setTeacher(existingTeacher);

        // Guardar el aula
        Classroom createdClassroom = service.createOrUpdateClassroom(classroom);*/
        return ResponseEntity.ok(null);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable("id") int id, @RequestBody Classroom classroom) {
        classroom.setId(id);
        Classroom updatedClassroom = service.createOrUpdateClassroom(classroom);
        return ResponseEntity.ok(updatedClassroom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable("id") int id) {
        service.deleteClassroom(id);
        return ResponseEntity.ok("Classroom deleted successfully");
    }
    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        List<Classroom> classrooms = service.getAllClassrooms();
        return ResponseEntity.ok(classrooms);
    }

}
