package com.josejavier.Controller;

import com.josejavier.model.Assessment;
import com.josejavier.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assessments") // Cambiado a "/assessments" para que coincida con la configuración del controlador
public class AssessmentController {
    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assessment> getAssessmentById(@PathVariable int id) {
        Optional<Assessment> assessment = assessmentService.getAssessmentById(id);
        return assessment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Assessment> createAssessment(@RequestBody Assessment assessment) {
        Assessment createdAssessment = assessmentService.createAssessment(assessment);
        return ResponseEntity.ok(createdAssessment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assessment> updateAssessment(@PathVariable(name = "id") Integer id, @RequestBody Assessment newAssessment) {
        Assessment updatedAssessment = assessmentService.updateAssessment(id, newAssessment);
        return ResponseEntity.ok(updatedAssessment);
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
