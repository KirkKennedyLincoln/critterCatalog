package com.udacity.jdnd.course3.critter.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;  

    @Autowired
    private PetRepository petRepository;

    public Customer saveCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setPetIds(dto.getPetIds());
        customer.setNotes(dto.getNotes());
        
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(Long petId) {
        Pet pet = petRepository
            .findById(petId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
            
        Long ownerId = pet.getOwnerId();
        return customerRepository
            .findById(ownerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
}
