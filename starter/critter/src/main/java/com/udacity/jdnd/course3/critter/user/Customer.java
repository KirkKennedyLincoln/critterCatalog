package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

@Entity
public class Customer extends User {
    @Column(name="customer_phone")
    private String phoneNumber;

    @Column(name="customer_notes")
    private String notes;

    @ElementCollection
    @Column(name="customer_petids")
    private List<Long> petIds;

    public String getNotes() {
        return notes;
    }
    public List<Long> getPetIds() {
        return petIds;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
