package com.josejavier.Controller;

import com.josejavier.model.Petition;
import com.josejavier.DTO.PetitionDTO;
import com.josejavier.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/petitions")
public class PetitionController {

    @Autowired
    private  PetitionService petitionService;

    @PostMapping
    public ResponseEntity<PetitionDTO> createPetition(@RequestBody PetitionDTO petitionDTO) {
        try {
            Petition petitionToCreate = new Petition(petitionDTO);
            Petition createdPetition = petitionService.createOrUpdatePetition(petitionToCreate);
            petitionDTO.setId(createdPetition.getId());
            return ResponseEntity.ok(petitionDTO);
        } catch (RuntimeException e) {
            // Manejar la excepción apropiadamente, por ejemplo, registrarla
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetitionDTO> updatePetition(@PathVariable("id") int id, @RequestBody PetitionDTO petitionDTO) {
        try {
            // Verificar si la petición con la ID dada existe
            Petition existingPetition = petitionService.getPetitionById(id);

            if (existingPetition == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            //Actualiza todos los campos de la petiicon existente con los valores proporcionados en el DTO
            existingPetition.setMessage(petitionDTO.getMessage());
            existingPetition.setState(petitionDTO.getState());
            existingPetition.setDate(petitionDTO.getDate());


            // Guardar la actualización en el servicio
            Petition updatedPetition = petitionService.createOrUpdatePetition(existingPetition);

            // Crear y devolver el DTO actualizado
            PetitionDTO updatedPetitionDTO = new PetitionDTO();
            updatedPetitionDTO.setId(updatedPetition.getId());
            updatedPetitionDTO.setMessage(updatedPetition.getMessage());
            updatedPetitionDTO.setState(updatedPetition.getState());
            updatedPetitionDTO.setDate(updatedPetition.getDate());

            return ResponseEntity.ok(updatedPetitionDTO);
        } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }   catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetition(@PathVariable("id") int id) {
        petitionService.deletePetition(id);
        return ResponseEntity.ok("Petition deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<PetitionDTO>> getAllPetitions() {
        List<Petition> petitions = petitionService.getAllPetitions();

        // Convertir la lista de entidades Classroom a una lista de DTOs
        List<PetitionDTO> petitionDTOs = petitions.stream()
                .map(Petition::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(petitionDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetitionDTO> getPetitionById(@PathVariable("id") int id) {
        Petition petition = petitionService.getPetitionById(id);
        PetitionDTO petitionDTO = petition.toDTO();
        return ResponseEntity.ok(petitionDTO);
    }
}
