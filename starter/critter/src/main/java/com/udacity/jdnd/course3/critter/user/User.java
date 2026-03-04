package com.udacity.jdnd.course3.critter.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user-generator"
    )
    @SequenceGenerator(
        name = "user-generator",
        sequenceName = "user-sequence"
    )

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
