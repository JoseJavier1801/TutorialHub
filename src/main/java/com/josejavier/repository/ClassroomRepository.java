package com.josejavier.repository;

import com.josejavier.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    @Query("SELECT u.id AS user_id, u.photo AS user_photo, u.name AS user_name, u.username AS user_username, " +
            "u.mail AS user_mail, u.password AS user_password, u.date AS user_date, " +
            "u.phone AS user_phone, t.id AS teacher_id, t.title AS teacher_title, t.biography AS teacher_biography, " +
            "c.id AS classroom_id, c.description, c.type, c.category, ST_AsText(c.location) AS location, " +
            "c.direction, c.postalCode, c.province, c.localidad " +
            "FROM Client u JOIN Teacher t ON u.id = t.id JOIN Classroom c ON t.id = c.teacher.id WHERE u.id = :userId")
    List<Object[]> getUserClassroomDetails(@Param("userId") Integer userId);

    @Query("SELECT c.id, c.category, c.type, c.direction, c.province, c.teacher.id AS teacher_id, " +
            "c.postalCode, c.description, c.teacher.id AS id_teacher " +
            "FROM Classroom c " +
            "WHERE c.category LIKE %:keyword% OR c.type LIKE %:keyword% OR " +
            "c.direction LIKE %:keyword% OR " +
            "c.province LIKE %:keyword% OR c.postalCode LIKE %:keyword% OR " +
            "c.description LIKE %:keyword%")
    List<Object[]> searchClassrooms(@Param("keyword") String keyword);



}
