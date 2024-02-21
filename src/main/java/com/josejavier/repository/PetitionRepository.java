package com.josejavier.repository;

import com.josejavier.model.Petition;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetitionRepository extends JpaRepository<Petition, Integer> {

    @Query("SELECT p FROM Petition p WHERE p.state = 'true'")
    List<Petition> getPetitionsWithTrueStatus();

    @Query("SELECT p FROM Petition p WHERE p.state = 'false'")
    List<Petition> getPetitionsWithFalseStatus();

    // Consulta SQL personalizada para contar las peticiones con estado nulo para un maestro
    @Query("SELECT COUNT(p.id) AS num_petitions_null FROM Petition p INNER JOIN Classroom c ON p.classroom.id = c.id WHERE p.state IS NULL AND c.teacher.id = ?1")
    int countNullStatePetitionsForTeacher(int teacherId);

    // Consulta SQL personalizada para obtener detalles del aula y del maestro para un usuario


    // Consulta SQL personalizada para contar las peticiones con estado nulo para un usuario
    @Query("SELECT COUNT(p.id) FROM Petition p INNER JOIN Classroom c ON p.classroom.id = c.id WHERE p.state IS NULL AND p.client.id = ?1")
    int countNullStatePetitionsForUser(int userId);

    @Query(value = "SELECT p.id, p.message, p.state, p.Date, c.name AS client_name, c.photo AS client_photo, c.mail AS client_email " +
            "FROM petition p " +
            "JOIN classroom cl ON p.id_classroom = cl.id " +
            "JOIN client c ON p.id_user = c.id " +
            "WHERE cl.id_teacher = ?1"+" AND p.state IN ('Denegada', 'Pendiente')", nativeQuery = true)
    List<Object[]> getMyPetitionTeacher(int teacherId);
    @Modifying
    @Transactional
    @Query("UPDATE Petition p SET p.state = :newState, p.message = :newMessage WHERE p.id = :petitionId")
    void updateStateAndMessageById(Integer petitionId, String newState, String newMessage);

    @Query(value = "SELECT p.id, p.message, p.state, p.Date, c.name AS client_name, c.photo AS client_photo, c.mail AS client_email " +
            "FROM petition p " +
            "JOIN classroom cl ON p.id_classroom = cl.id " +
            "JOIN client c ON p.id_user = c.id " +
            "WHERE c.id = ?1 "+"AND p.state IN ('Denegada', 'Aceptada')  ", nativeQuery = true)
    List<Object[]> getPetitionsByClientIdAndState(int clientId);



}
