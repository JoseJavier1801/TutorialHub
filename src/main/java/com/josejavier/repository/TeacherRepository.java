package com.josejavier.repository;

import com.josejavier.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("SELECT t FROM Teacher t WHERE t.name = ?1")
    public Teacher findTeacherByName(String name);

    @Query("SELECT t FROM Teacher t WHERE t.id = ?1")
    Teacher findTeacherById(int id);

}
