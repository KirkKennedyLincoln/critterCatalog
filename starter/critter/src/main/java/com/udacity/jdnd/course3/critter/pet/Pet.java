package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Pet {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "customer-generator"
    )
    @SequenceGenerator(
        name = "customer-generator",
        sequenceName = "customer-sequence"
    )
    private Long id;

    @Column(name = "pet_type")
    private PetType petType;

    @Column(name = "pet_name")
    private String name;
    
    @Column(name = "pet_ownerid")
    private Long ownerId;
    
    @Column(name = "pet_birthday")
    private LocalDate birthday;

    @Column(name = "pet_notes")
    private String notes;

    public LocalDate getBirthday() {
        return birthday;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getNotes() {
        return notes;
    }
    public Long getOwnerId() {
        return ownerId;
    }
    public PetType getPetType() {
        return petType;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    public void setPetType(PetType petType) {
        this.petType = petType;
    }
}
