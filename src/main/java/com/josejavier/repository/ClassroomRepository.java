package com.josejavier.repository;

import com.josejavier.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    @Query("SELECT c.id AS client_id, c.photo AS client_photo, c.name AS client_name, " +
            "c.mail AS client_mail, c.date AS client_date, c.phone AS client_phone, " +
            "t.title AS teacher_title, t.biography AS teacher_biography, " +
            "cl.id AS classroom_id, cl.description, cl.type, cl.category, " +
            "ST_AsText(cl.location) AS location, cl.direction, cl.postalCode AS postal_code, " +
            "cl.province, cl.localidad, cl.duration " +
            "FROM Client c " +
            "JOIN Teacher t ON c.id = t.id " +
            "JOIN Classroom cl ON t.id = cl.teacher.id " +
            "WHERE c.id = :userId")
    List<Object[]> getUserClassroomDetails(@Param("userId") Integer userId);




    @Query("SELECT c.id, c.category, c.type, c.direction, c.province, c.teacher.id AS teacher_id, " +
            "c.postalCode, c.description, c.teacher.id AS id_teacher " +
            "FROM Classroom c " +
            "WHERE c.category LIKE %:keyword% OR c.type LIKE %:keyword% OR " +
            "c.direction LIKE %:keyword% OR " +
            "c.province LIKE %:keyword% OR c.postalCode LIKE %:keyword% OR " +
            "c.description LIKE %:keyword%")
    List<Object[]> searchClassrooms(@Param("keyword") String keyword);
    @Query(value = "SELECT " +
            "c.id AS classroom_id, " +
            "c.description AS classroom_description, " +
            "c.type AS classroom_type, " +
            "c.category AS classroom_category, " +
            "(c.location) AS classroom_location, " +
            "c.direction AS classroom_direction, " +
            "c.postal_code AS classroom_postal_code, " +
            "c.province AS classroom_province, " +
            "c.localidad AS classroom_localidad, " +
            "c.duration AS classroom_duration, " +
            "c.id_teacher AS classroom_id_teacher, " +
            "cl.name AS teacher_name, " +
            "cl.photo AS teacher_photo, " +
            "cl.mail AS teacher_mail, " +
            "cl.phone AS teacher_phone, " +
            "t.biography AS teacher_biography, " +
            "t.title AS teacher_title " +
            "FROM " +
            "classroom c " +
            "JOIN teacher t ON t.id = c.id_teacher " +
            "JOIN client cl ON cl.id = t.id " +
            "WHERE " +
            "t.id = :teacherId", nativeQuery = true)
    List<Object[]> findByTeacherId(@Param("teacherId") Integer teacherId);

    @Query(value = "SELECT " +
            "c.id AS classroom_id, " +
            "c.description AS classroom_description, " +
            "c.type AS classroom_type, " +
            "c.category AS classroom_category, " +
            "c.location AS classroom_location, " +
            "c.direction AS classroom_direction, " +
            "c.postal_code AS classroom_postal_code, " +
            "c.province AS classroom_province, " +
            "c.localidad AS classroom_localidad, " +
            "c.duration AS classroom_duration, " +
            "c.id_teacher AS classroom_id_teacher, " +
            "cl.name AS teacher_name, " +
            "cl.photo AS teacher_photo, " +
            "cl.mail AS teacher_mail, " +
            "cl.phone AS teacher_phone, " +
            "t.biography AS teacher_biography, " +
            "t.title AS teacher_title " +
            "FROM " +
            "classroom c " +
            "JOIN teacher t ON t.id = c.id_teacher " +
            "JOIN client cl ON cl.id = t.id", nativeQuery = true)
    List<Object[]> getallClassroomDetails();


}
