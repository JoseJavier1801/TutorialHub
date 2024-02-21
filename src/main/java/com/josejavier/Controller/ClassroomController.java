package com.josejavier.Controller;

import com.josejavier.model.Classroom;
import com.josejavier.DTO.ClassroomDTO;
import com.josejavier.model.Teacher;
import com.josejavier.service.ClassroomService;
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
            // Crear una instancia de Classroom a partir de ClassroomDTO
            Classroom classroomToCreate = new Classroom();
            classroomToCreate.setDescription(classroom.getDescription());
            classroomToCreate.setType(classroom.getType());
            classroomToCreate.setCategory(classroom.getCategory());
            // Asignar el ID del profesor directamente si está presente
            if (classroom.getTeacherID() != 0) {
                Teacher teacher = new Teacher();
                teacher.setId(classroom.getTeacherID());
                classroomToCreate.setTeacher(teacher);
            }
            // Guardar la nueva instancia de Classroom
            Classroom createdClassroom = classroomService.createOrUpdateClassroom(classroomToCreate);
            // Actualizar el ID en el DTO
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
    public ResponseEntity<ClassroomDTO> updateClassroom(@PathVariable("id") int id, @RequestBody ClassroomDTO classroomDTO) {
        try {
            // Verificar si el aula con la ID dada existe
            Classroom existingClassroom = classroomService.getClassroomById(Integer.valueOf(id));

            if (existingClassroom == null) {
                // Manejar la situación en la que el aula no existe
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Actualizar todos los campos del aula existente con los valores proporcionados en el DTO
            existingClassroom.setDescription(classroomDTO.getDescription());
            existingClassroom.setType(classroomDTO.getType());
            existingClassroom.setCategory(classroomDTO.getCategory());
            existingClassroom.setDirection(classroomDTO.getDirection());
            existingClassroom.setPostalCode(classroomDTO.getPostalCode());
            existingClassroom.setProvince(classroomDTO.getProvince());
            existingClassroom.setLocalidad(classroomDTO.getLocalidad());

            // Guardar la actualización en el servicio
            Classroom updatedClassroom = classroomService.createOrUpdateClassroom(existingClassroom);

            // Crear y devolver el DTO actualizado
            ClassroomDTO updatedClassroomDTO = new ClassroomDTO();
            updatedClassroomDTO.setId(updatedClassroom.getId());
            updatedClassroomDTO.setDescription(updatedClassroom.getDescription());
            updatedClassroomDTO.setType(updatedClassroom.getType());
            updatedClassroomDTO.setCategory(updatedClassroom.getCategory());
            updatedClassroomDTO.setDirection(updatedClassroom.getDirection());
            updatedClassroomDTO.setPostalCode(updatedClassroom.getPostalCode());
            updatedClassroomDTO.setProvince(updatedClassroom.getProvince());
            updatedClassroomDTO.setLocalidad(updatedClassroom.getLocalidad());


            return ResponseEntity.ok(updatedClassroomDTO);
        } catch (RuntimeException e) {
            // Manejar la excepción apropiadamente, por ejemplo, registrarla
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable("id") int id) {
        classroomService.deleteClassroom(Integer.valueOf(id));
        return ResponseEntity.ok("Classroom deleted successfully");
    }



    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable("id") int id) {
        Classroom classroom = classroomService.getClassroomById(Integer.valueOf(id));
        ClassroomDTO classroomDTO = classroom.toDTO();
        return ResponseEntity.ok(classroomDTO);
    }
    @GetMapping("/userClassrooms/{userId}")
    public List<Object[]> getUserClassrooms(@PathVariable Integer userId) {
        return classroomService.getUserClassroomDetails(userId);
    }
    @GetMapping("/teacher/{teacherId}")
    public List<ClassroomDTO> getClassroomsByTeacherId(@PathVariable("teacherId") Integer teacherId) {

        return classroomService.getClassroomsByTeacherId(teacherId);
    }
    @GetMapping("/details")
    public List<ClassroomDTO> getAllClassroomDetails() {
        List<ClassroomDTO> result = classroomService.getAllClassroomDetails();
        System.out.println(result);
        return result;
    }
    @GetMapping("/seeker")
    public List<ClassroomDTO> searchClassrooms(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String localidad,
            @RequestParam(required = false) String postalCode) {
        return classroomService.getAllClassroomSeeker(category, localidad, postalCode);
    }

}