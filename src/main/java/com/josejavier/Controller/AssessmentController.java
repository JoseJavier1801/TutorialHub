package com.josejavier.Controller;

import com.josejavier.DTO.AssessmentDTO;
import com.josejavier.model.Assessment;
import com.josejavier.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/assessments") // Cambiado a "/assessments" para que coincida con la configuración del controlador
public class AssessmentController {

    @Autowired
    private  AssessmentService assessmentService;


    @GetMapping
    public ResponseEntity<List<AssessmentDTO>> getAllAssessments() {
        List<Assessment> assessments = assessmentService.getAllAssessments();
        List<AssessmentDTO> assessmentDTOs = assessments.stream()
                .map(Assessment::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(assessmentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentDTO> getAssessmentById(@PathVariable("id") int id) {
        Assessment assessment = assessmentService.getAssessmentById(id);
        AssessmentDTO assessmentDTO= assessment.toDTO();
        return ResponseEntity.ok(assessmentDTO);
    }

    @PostMapping
    public ResponseEntity<Assessment> createAssessment(@RequestBody AssessmentDTO assessmentDTO) {
        try{
            Assessment assessment = new Assessment(assessmentDTO);
            Assessment createdAssessment = assessmentService.createOrUpdateAssement(assessment);
            return ResponseEntity.ok(createdAssessment);
        }catch (RuntimeException e){
            // Manejar la excepción apropiadamente, por ejemplo, registrarla
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<AssessmentDTO> updateAssessment(@PathVariable(name = "id") Integer id, @RequestBody AssessmentDTO assessmentDTO) {
        try{
            //verifica si la valoracion con la id dada existe
            Assessment existingAssessment = assessmentService.getAssessmentById(id);
            if(existingAssessment == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            //Actualiza todos los campos de la valoracion existentes con los valores proporcionados en el DTO
            existingAssessment.setAssessment(assessmentDTO.getAssessment());
            existingAssessment.setComment(assessmentDTO.getComment());

            //Guarda la actualizacion en el servicio
            Assessment updatedAssessment = assessmentService.createOrUpdateAssement(existingAssessment);

            //Crea y devuelve el DTO actualizado
            AssessmentDTO updatedAssessmentDTO = new AssessmentDTO();
            updatedAssessmentDTO.setId(updatedAssessment.getId());
            updatedAssessmentDTO.setAssessment(updatedAssessment.getAssessment());
            updatedAssessmentDTO.setComment(updatedAssessment.getComment());
            return ResponseEntity.ok(updatedAssessmentDTO);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable(name = "id") Integer id) {
        assessmentService.deleteAssessment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Assessment> getAssessmentsByTeacherId(@PathVariable int teacherId) {
        return assessmentService.getAssessmentsByTeacherId(teacherId);
    }
}
