package com.josejavier.Controller;

import com.josejavier.model.Petition;
import com.josejavier.model.PetitionDTO;
import com.josejavier.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Petition> updatePetition(@PathVariable("id") int id, @RequestBody Petition petition) {
        petition.setId(id);
        Petition updatedPetition = petitionService.createOrUpdatePetition(petition);
        return ResponseEntity.ok(updatedPetition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetition(@PathVariable("id") int id) {
        petitionService.deletePetition(id);
        return ResponseEntity.ok("Petition deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Petition>> getAllPetitions() {
        List<Petition> petitions = petitionService.getAllPetitions();
        return ResponseEntity.ok(petitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Petition> getPetitionById(@PathVariable("id") int id) {
        Petition petition = petitionService.getPetitionById(id);
        return ResponseEntity.ok(petition);
    }
}
