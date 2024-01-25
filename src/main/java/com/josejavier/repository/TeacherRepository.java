package com.josejavier.repository;

import com.josejavier.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("SELECT t FROM Teacher t WHERE t.username = :username")
    Teacher findByUsername(String username);

    @Query("SELECT t FROM Teacher t WHERE t.id = :id")
    Teacher FindbyId(int id);
}
