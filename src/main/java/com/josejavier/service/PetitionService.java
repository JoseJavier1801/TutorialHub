package com.josejavier.service;

import com.josejavier.model.Petition;
import com.josejavier.model.Client;
import com.josejavier.model.Classroom;
import com.josejavier.repository.PetitionRepository;
import com.josejavier.repository.ClientRepository;
import com.josejavier.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetitionService {

    @Autowired
    private  PetitionRepository petitionRepository;
    @Autowired
    private  ClientRepository clientRepository;
    @Autowired
    private ClassroomRepository classroomRepository;


    public Petition createOrUpdatePetition(Petition petition) {
        Petition newPetition = null;
        try {
            // Verificar si el cliente y el aula existen antes de guardar la petición
            Integer clientId = petition.getClient() != null ? petition.getClient().getId() : null;
            Integer classroomId = petition.getClassroom() != null ? petition.getClassroom().getId() : null;

            if (clientId == null || getClientById(clientId) == null) {
                // El cliente no existe, puedes manejarlo según tus necesidades
                throw new RuntimeException("Client does not exist");
            }

            if (classroomId == null || getClassroomById(classroomId) == null) {
                // El aula no existe, puedes manejarlo según tus necesidades
                throw new RuntimeException("Classroom does not exist");
            }

            newPetition = petitionRepository.save(petition);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating/updating petition", e);
        }
        return newPetition;
    }

    public void deletePetition(Integer id) {
        try {
            petitionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting petition", e);
        }
    }

    public List<Petition> getAllPetitions() {
        try {
            List<Petition> petitions = petitionRepository.findAll();
            return petitions;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving petitions", e);
        }
    }

    public Petition getPetitionById(Integer id) {
        try {
            Optional<Petition> optionalPetition = petitionRepository.findById(id);
            return optionalPetition.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving petition by ID", e);
        }
    }

    public Client getClientById(Integer clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    public Classroom getClassroomById(Integer classroomId) {
        return classroomRepository.findById(classroomId).orElse(null);
    }


}
