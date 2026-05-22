package de.demo.tierhandlung.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PetResponse(
        Long id,
        String name,
        String species,
        LocalDate dateOfBirth,
        BigDecimal price,
        boolean available,
        Long categoryId,
        String categoryName) {

    static PetResponse from(Pet pet) {
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getDateOfBirth(),
                pet.getPrice(),
                pet.isAvailable(),
                pet.getCategory().getId(),
                pet.getCategory().getName());
    }
}

