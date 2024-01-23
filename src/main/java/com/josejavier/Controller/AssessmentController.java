package com.josejavier.Controller;

import com.josejavier.model.Assessment;
import com.josejavier.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessments")
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
    public Assessment getAssessmentById(@PathVariable int id) {
        return assessmentService.getAssessmentById(id).orElse(null);
    }

    @PostMapping
    public Assessment createAssessment(@RequestBody Assessment assessment) {
        return assessmentService.createAssessment(assessment);
    }

    @PutMapping("/{id}")
    public Assessment updateAssessment(@PathVariable(name = "id") Integer id, @RequestBody Assessment newAssessment) {
        return assessmentService.updateAssessment(id, newAssessment);
    }

    @DeleteMapping("/{id}")
    public void deleteAssessment(@PathVariable(name = "id") Integer id) {
        assessmentService.deleteAssessment(id);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Assessment> getAssessmentsByTeacherId(@PathVariable int teacherId) {
        return assessmentService.getAssessmentsByTeacherId(teacherId);
    }
}
