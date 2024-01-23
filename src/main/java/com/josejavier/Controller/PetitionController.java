package com.josejavier.Controller;

import com.josejavier.model.Petition;
import com.josejavier.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petitions")
public class PetitionController {

    @Autowired
    PetitionService service;

    @GetMapping
    public ResponseEntity<List<Petition>> getAllPetitions() {
        List<Petition> petitions = service.getAllPetitions();
        return ResponseEntity.ok(petitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Petition> getPetitionById(@PathVariable("id") int id) {
        Petition petition = service.getPetitionById(id);
        return ResponseEntity.ok(petition);
    }

    @PostMapping
    public ResponseEntity<Petition> createPetition(@RequestBody Petition petition) {
        Petition createdPetition = service.createOrUpdatePetition(petition);
        return ResponseEntity.ok(createdPetition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Petition> updatePetition(@PathVariable("id") int id, @RequestBody Petition petition) {
        petition.setId(id);
        Petition updatedPetition = service.createOrUpdatePetition(petition);
        return ResponseEntity.ok(updatedPetition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetition(@PathVariable("id") int id) {
        service.deletePetition(id);
        return ResponseEntity.ok("Petition deleted successfully");
    }
}
