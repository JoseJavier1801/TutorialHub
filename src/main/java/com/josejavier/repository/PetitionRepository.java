package com.josejavier.repository;

import com.josejavier.model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface PetitionRepository extends JpaRepository<Petition, Integer> {


}