package com.josejavier.service;

import com.josejavier.model.Client;
import com.josejavier.model.Petition;
import com.josejavier.repository.PetitionRepository;
import com.josejavier.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Petition createOrUpdatePetition(Petition petition) {
        try {
            // Verificar si el cliente existe antes de guardar la petición
            Client client = petition.getClient();
            if (client != null && client.getId() != 0) {
                // Cliente existe, recuperarlo de la base de datos
                Optional<Client> existingClient = clientRepository.findById(client.getId());

                if (existingClient.isPresent()) {
                    // Asociar el cliente persistente a la petición
                    petition.setClient(existingClient.get());
                } else {
                    // Cliente no existe, puedes manejarlo según tus necesidades
                    throw new RuntimeException("Client does not exist");
                }
            } else {
                // Cliente no proporcionado, puedes manejarlo según tus necesidades
                throw new RuntimeException("Client is required");
            }

            return petitionRepository.save(petition);
        } catch (Exception e) {
            throw new RuntimeException("Error creating/updating petition", e);
        }
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
            return petitionRepository.findAll();
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
}
