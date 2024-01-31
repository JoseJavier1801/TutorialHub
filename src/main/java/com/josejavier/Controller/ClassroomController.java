package com.josejavier.Controller;

import com.josejavier.model.Classroom;
import com.josejavier.model.ClassroomDTO;
import com.josejavier.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<ClassroomDTO> createClassroom(@RequestBody ClassroomDTO classroom) {
        try {
            Classroom classroomToCreate = new Classroom(classroom);
            Classroom createdClassroom = classroomService.createOrUpdateClassroom(classroomToCreate);
            classroom.setId(createdClassroom.getId());
            return ResponseEntity.ok(classroom);
        } catch (RuntimeException e) {
            // Manejar la excepción apropiadamente, por ejemplo, registrarla
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable("id") int id, @RequestBody Classroom classroom) {
        classroom.setId(id);
        Classroom updatedClassroom = classroomService.createOrUpdateClassroom(classroom);
        return ResponseEntity.ok(updatedClassroom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable("id") int id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok("Classroom deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassrooms();

        // Convertir la lista de entidades Classroom a una lista de DTOs
        List<ClassroomDTO> classroomDTOs = classrooms.stream()
                .map(Classroom::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(classroomDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable("id") int id) {
        Classroom classroom = classroomService.getClassroomById(id);
        ClassroomDTO classroomDTO = classroom.toDTO();
        return ResponseEntity.ok(classroomDTO);
    }
}
