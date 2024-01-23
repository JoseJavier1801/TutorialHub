package com.josejavier.service;

import com.josejavier.model.Petition;
import com.josejavier.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetitionService {

    private final PetitionRepository petitionRepository;

    @Autowired
    public PetitionService(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }

    public List<Petition> getAllPetitions() {
        return petitionRepository.findAll();
    }

    public Petition getPetitionById(int id) {
        Optional<Petition> optionalPetition = petitionRepository.findById(id);
        return optionalPetition.orElse(null);
    }

    public Petition createOrUpdatePetition(Petition petition) {
        return petitionRepository.save(petition);
    }

    public void deletePetition(int id) {
        petitionRepository.deleteById(id);
    }




}
