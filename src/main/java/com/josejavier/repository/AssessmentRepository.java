package com.josejavier.repository;

import com.josejavier.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

    /**
     *  funcion par optener las calificaciones de un profesor por su ID
     * @param teacherId
     * @return List<Assessment>
     */
    @Query("SELECT a FROM Assessment a WHERE a.teacher.id = :teacherId")
    List<Assessment> findByTeacherId(@Param("teacherId") Integer teacherId);

    @Query("SELECT AVG(a.assessment) FROM Assessment a WHERE a.teacher.id = :teacherId")
    Double findAverageRatingByTeacherId(@Param("teacherId") Integer teacherId);

    default double levelTeacher(Integer teacherId) {
        Double averageRating = findAverageRatingByTeacherId(teacherId);
        if (averageRating == null) {
            return 0.0; // No hay valoraciones para este profesor.
        } else if (averageRating >= 4.5) {
            return 5.0; // Excelente
        } else if (averageRating >= 4.0) {
            return 4.0; // Muy bueno
        } else if (averageRating >= 3.5) {
            return 3.0; // Bueno
        } else if (averageRating >= 3.0) {
            return 2.0; // Aceptable
        } else {
            return 1.0; // Necesita mejorar
        }
    }
}

