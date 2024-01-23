package com.josejavier.repository;

import com.josejavier.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {
    List<Assessment> findByTeacherId(int teacherId);
}
