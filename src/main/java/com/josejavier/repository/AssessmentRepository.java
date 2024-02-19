package com.josejavier.repository;

import com.josejavier.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

    @Query("SELECT a FROM Assessment a WHERE a.teacher.id = :teacherId")
    List<Assessment> findByTeacherId(@Param("teacherId") Integer teacherId);

}
