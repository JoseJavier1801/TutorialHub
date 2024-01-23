package com.josejavier.service;

import com.josejavier.model.Assessment;
import com.josejavier.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentService {
    private final AssessmentRepository assessmentRepository;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Optional<Assessment> getAssessmentById(int id) {
        return assessmentRepository.findById(id);
    }

    public Assessment createAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    public Assessment updateAssessment(int id, Assessment newAssessment) {
        return assessmentRepository.findById(id)
                .map(assessment -> {
                    assessment.setComment(newAssessment.getComment());
                    assessment.setAssessment(newAssessment.getAssessment());
                    assessment.setTeacher(newAssessment.getTeacher());
                    assessment.setUser(newAssessment.getUser());
                    return assessmentRepository.save(assessment);
                })
                .orElse(null);
    }

    public void deleteAssessment(int id) {
        assessmentRepository.deleteById(id);
    }

    public List<Assessment> getAssessmentsByTeacherId(int teacherId) {
        return assessmentRepository.findByTeacherId(teacherId);
    }
}
