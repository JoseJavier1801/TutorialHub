package com.josejavier.service;

import com.josejavier.model.Petition;
import com.josejavier.model.Client;
import com.josejavier.model.Classroom;
import com.josejavier.repository.PetitionRepository;
import com.josejavier.repository.ClientRepository;
import com.josejavier.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Petition> getMyPetitionTeacher(int teacherId) {
        List<Object[]> results = petitionRepository.getMyPetitionTeacher(teacherId);
        List<Petition> petitions = new ArrayList<>();
        for (Object[] result : results) {
            Petition petition = new Petition();

            // Asignación de propiedades según el orden de la consulta SQL
            petition.setId((Integer) result[0]);
            petition.setMessage((String) result[1]);
            petition.setState((String) result[2]);

            // Conversión de java.sql.Date a java.time.LocalDate
            Date dateSql = (Date) result[3];
            if (dateSql != null) {
                petition.setDate(dateSql.toLocalDate());
            } else {
                // Manejar el caso en el que dateSql sea nulo
                // Por ejemplo, establecer una fecha predeterminada o lanzar una excepción
                // Aquí se establece la fecha actual como predeterminada
                petition.setDate(LocalDate.now());
            }

            // Crear un objeto Client y asignar su ID
            Client client = new Client();

            // Asignar el resto de propiedades del cliente
            client.setName((String) result[4]); // Suponiendo que el índice 5 es el campo name del cliente

            // Convertir la foto a una cadena Base64
            client.setPhoto((byte[]) result[5]);

            client.setMail((String) result[6]); // Suponiendo que el índice 7 es el campo mail del cliente

            // Establecer el objeto Client en la Petición
            petition.setClient(client);

            // Agregar la Petición a la lista
            petitions.add(petition);
        }

        return petitions;
    }

    public void updatePetitionStateAndMessage(Integer petitionId, String newState, String newMessage) {
        petitionRepository.updateStateAndMessageById(petitionId, newState, newMessage);
    }

    public List<Petition> getPetitionsByClientIdAndState(int clientId) {
        List<Object[]> results = petitionRepository.getPetitionsByClientIdAndState(clientId);
        List<Petition> petitions = new ArrayList<>();
        for (Object[] result : results) {
            Petition petition = new Petition();

            // Asignación de propiedades según el orden de la consulta SQL
            petition.setId((Integer) result[0]);
            petition.setMessage((String) result[1]);
            petition.setState((String) result[2]);

            // Conversión de java.sql.Date a java.time.LocalDate
            Date dateSql = (Date) result[3];
            if (dateSql != null) {
                petition.setDate(dateSql.toLocalDate());
            } else {
                // Manejar el caso en el que dateSql sea nulo
                // Por ejemplo, establecer una fecha predeterminada o lanzar una excepción
                // Aquí se establece la fecha actual como predeterminada
                petition.setDate(LocalDate.now());
            }

            // Crear un objeto Client y asignar su ID
            Client client = new Client();

            // Asignar el resto de propiedades del cliente
            client.setName((String) result[4]); // Suponiendo que el índice 5 es el campo name del cliente

            // Convertir la foto a una cadena Base64
            client.setPhoto((byte[]) result[5]);

            client.setMail((String) result[6]); // Suponiendo que el índice 7 es el campo mail del cliente

            // Establecer el objeto Client en la Petición
            petition.setClient(client);

            // Agregar la Petición a la lista
            petitions.add(petition);
        }

        return petitions;
    }


}