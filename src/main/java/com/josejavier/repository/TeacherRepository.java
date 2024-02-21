package com.josejavier.repository;

import com.josejavier.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {


    @Query("select c.username,c.name,t.biography,t.title from Client c join Teacher t on c.id=t.id where c.id = :id")
    Teacher FindbyId(int id);

    @Query("SELECT t FROM Teacher t WHERE t.username = :username or t.password = :password")
    Teacher findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    @Query("UPDATE Teacher t SET t.title = :title, t.biography = :biography WHERE t.id = :id")
    Teacher updateTeacher(@Param("id") int id, @Param("title") String title, @Param("biography") String biography);

    @Query("DELETE FROM Teacher t WHERE t.id = :id")
    void deleteTeacher(@Param("id") int id);
}
