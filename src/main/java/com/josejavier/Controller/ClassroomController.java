package com.josejavier.Controller;

import com.josejavier.model.Classroom;
import com.josejavier.model.ClassroomDTO;
import com.josejavier.model.Teacher;
import com.josejavier.service.ClassroomService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Classroom> updateClassroom(@PathVariable(name = "id") Integer id, @RequestBody ClassroomDTO classroomDTO) {
        try {
            Classroom existingClassroom = classroomService.getClassroomById(id);

            if (existingClassroom != null) {
                // Actualizar la información del aula
                existingClassroom.setDescription(classroomDTO.getDescription());
                existingClassroom.setType(classroomDTO.getType());
                existingClassroom.setCategory(classroomDTO.getCategory());

                // Actualizar las coordenadas del Point (location)
                GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
                Point updatedPoint = factory.createPoint(new Coordinate(classroomDTO.getLng(), classroomDTO.getLat()));
                existingClassroom.setLocation(updatedPoint);

                existingClassroom.setDirection(classroomDTO.getDirection());
                existingClassroom.setPostalCode(classroomDTO.getPostalCode());
                existingClassroom.setProvince(classroomDTO.getProvince());
                existingClassroom.setLocalidad(classroomDTO.getLocalidad());

                // Cargar la información del profesor desde la base de datos
                Teacher existingTeacher = classroomService.getTeacherById(classroomDTO.getTeacherID());

                if (existingTeacher == null) {
                    // El profesor no existe, puedes manejarlo según tus necesidades
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }

                // Actualizar el profesor del aula
                existingClassroom.setTeacher(existingTeacher);

                // Guardar la actualización
                Classroom updatedClassroom = classroomService.createOrUpdateClassroom(existingClassroom);
                System.out.println("La clase se actualizo correctamente");
                return ResponseEntity.ok(updatedClassroom);

            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable("id") int id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok("Classroom deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        return ResponseEntity.ok(classrooms);
    }
}
