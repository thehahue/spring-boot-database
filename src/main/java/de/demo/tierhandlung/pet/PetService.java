package de.demo.tierhandlung.pet;

import java.util.List;

import de.demo.tierhandlung.category.PetCategory;
import de.demo.tierhandlung.category.PetCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetCategoryRepository categoryRepository;

    public PetService(PetRepository petRepository, PetCategoryRepository categoryRepository) {
        this.petRepository = petRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<PetResponse> findPets(Long categoryId) {
        List<Pet> pets = categoryId == null
                ? petRepository.findAllByOrderByNameAsc()
                : petRepository.findAllByCategoryIdOrderByNameAsc(categoryId);

        return pets.stream().map(PetResponse::from).toList();
    }

    public PetResponse findPet(long id) {
        return PetResponse.from(getPet(id));
    }

    public PetResponse createPet(PetRequest request) {
        PetCategory category = getCategory(request.categoryId());
        Pet pet = new Pet(
                request.name(),
                request.species(),
                request.dateOfBirth(),
                request.price(),
                request.available(),
                category);

        return PetResponse.from(petRepository.save(pet));
    }

    public PetResponse updatePet(long id, PetRequest request) {
        Pet pet = getPet(id);
        PetCategory category = getCategory(request.categoryId());

        pet.update(
                request.name(),
                request.species(),
                request.dateOfBirth(),
                request.price(),
                request.available(),
                category);

        return PetResponse.from(petRepository.save(pet));
    }

    public void deletePet(long id) {
        petRepository.delete(getPet(id));
    }

    private Pet getPet(long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tier nicht gefunden"));
    }

    private PetCategory getCategory(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kategorie nicht gefunden"));
    }
}
