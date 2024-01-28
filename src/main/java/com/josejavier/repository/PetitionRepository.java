package com.josejavier.repository;

import com.josejavier.model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface PetitionRepository extends JpaRepository<Petition, Integer> {

    // Consulta SQL personalizada para obtener peticiones con estado true
    @Query("SELECT p FROM Petition p WHERE p.state = 'true'")
    List<Petition> getPetitionsWithTrueStatus();

    // Consulta SQL personalizada para obtener peticiones con estado false
    @Query("SELECT p FROM Petition p WHERE p.state = 'false'")
    List<Petition> getPetitionsWithFalseStatus();

}