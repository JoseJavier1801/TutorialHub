package com.josejavier.repository;

import com.josejavier.model.teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<teacher, Integer> {

}
