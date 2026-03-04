package com.udacity.jdnd.course3.critter.pet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PetService {
    @Autowired
    private CustomerRepository customerRepository;  

    @Autowired
    private PetRepository petRepository;

    public Pet savePet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setBirthday(petDTO.getBirthDate());
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setNotes(petDTO.getNotes());
        pet.setOwnerId(petDTO.getOwnerId());
        pet.setPetType(petDTO.getType());

        Customer customer = customerRepository
            .findById(pet.getOwnerId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
        List<Long> petIds = customer.getPetIds();
        Pet saved = petRepository.save(pet);
        if (petIds == null) {
            petIds = new ArrayList<>();
        }
        petIds.add(saved.getId());
        customer.setPetIds(petIds);
        customerRepository.save(customer);

        return saved;
    }

    public Pet getPet(Long petId) {
        return petRepository
            .findById(petId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        Customer customer = customerRepository
            .findById(ownerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));

        List<Long> petIds = customer.getPetIds();
        return petRepository.findAllById(petIds);
    }
}
