package de.demo.tierhandlung.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetRequest(
        @NotBlank String name,
        @NotBlank String species,
        LocalDate dateOfBirth,
        @NotNull @DecimalMin("0.00") BigDecimal price,
        boolean available,
        @NotNull Long categoryId) {
}

