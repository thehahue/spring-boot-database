package de.demo.tierhandlung.pet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PetRepositoryTests {

    @Autowired
    private PetRepository petRepository;

    @Test
    void findsSeededPetsByCategory() {
        var pets = petRepository.findAllByCategoryIdOrderByNameAsc(1L);

        assertThat(pets).extracting(Pet::getName).containsExactly("Momo");
    }
}
