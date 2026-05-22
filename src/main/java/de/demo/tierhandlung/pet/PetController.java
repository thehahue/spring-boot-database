package de.demo.tierhandlung.pet;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<PetResponse> listPets(@RequestParam(required = false) Long categoryId) {
        return petService.findPets(categoryId);
    }

    @GetMapping("/{id}")
    public PetResponse getPet(@PathVariable long id) {
        return petService.findPet(id);
    }

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@Valid @RequestBody PetRequest request,
            UriComponentsBuilder uriBuilder) {
        PetResponse pet = petService.createPet(request);
        URI location = uriBuilder.path("/api/pets/{id}").build(pet.id());

        return ResponseEntity.created(location).body(pet);
    }

    @PutMapping("/{id}")
    public PetResponse updatePet(@PathVariable long id, @Valid @RequestBody PetRequest request) {
        return petService.updatePet(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}

