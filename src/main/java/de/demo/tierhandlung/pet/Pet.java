package de.demo.tierhandlung.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.demo.tierhandlung.category.PetCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean available;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private PetCategory category;

    protected Pet() {
    }

    public Pet(String name, String species, LocalDate dateOfBirth, BigDecimal price, boolean available,
            PetCategory category) {
        this.name = name;
        this.species = species;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.available = available;
        this.category = category;
    }

    public void update(String name, String species, LocalDate dateOfBirth, BigDecimal price, boolean available,
            PetCategory category) {
        this.name = name;
        this.species = species;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.available = available;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public PetCategory getCategory() {
        return category;
    }
}
