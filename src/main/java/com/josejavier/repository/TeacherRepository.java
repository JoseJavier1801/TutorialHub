package com.josejavier.repository;

import com.josejavier.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {


    @Query("select c.username,c.name,t.biography,t.title from Client c join Teacher t on c.id=t.id where c.id = :id")
    Teacher FindbyId(int id);

    @Query("UPDATE Teacher t SET t.title = :title, t.biography = :biography WHERE t.id = :id")
    Teacher Update(Teacher t);

    @Query("DELETE FROM Teacher t WHERE t.id = :id")
    Teacher deleteTeacher(int id);
}
