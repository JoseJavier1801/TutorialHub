package com.josejavier.repository;

import com.josejavier.model.Petition;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, Integer> {
    /**
     *  funcion par optener las peticiones de un profesor por su ID
     * @param teacherId
     * @return
     */
    @Query(value = "SELECT p.id, p.message, p.state, p.Date, c.name AS client_name, c.photo AS client_photo, c.mail AS client_email " +
            "FROM petition p " +
            "JOIN classroom cl ON p.id_classroom = cl.id " +
            "JOIN client c ON p.id_user = c.id " +
            "WHERE cl.id_teacher = ?1"+" AND p.state IN ('Denegada', 'Pendiente')", nativeQuery = true)
    List<Object[]> getMyPetitionTeacher(int teacherId);

    /**
     *  funcion para poder modifcar la peticion por su ID
     * @param petitionId
     * @param newState
     * @param newMessage
     */
    @Modifying
    @Transactional
    @Query("UPDATE Petition p SET p.state = :newState, p.message = :newMessage WHERE p.id = :petitionId")
    void updateStateAndMessageById(Integer petitionId, String newState, String newMessage);

    /**
     *  funcion par optener las peticiones de un cliente por su ID y el estado
     * @param clientId
     * @return List<Object[]>
     */
    @Query(value = "SELECT p.id, p.message, p.state, p.Date, c.name AS client_name, c.photo AS client_photo, c.mail AS client_email " +
            "FROM petition p " +
            "JOIN classroom cl ON p.id_classroom = cl.id " +
            "JOIN client c ON p.id_user = c.id " +
            "WHERE c.id = ?1 "+"AND p.state IN ('Denegada', 'Aceptada')  ", nativeQuery = true)
    List<Object[]> getPetitionsByClientIdAndState(int clientId);



}
