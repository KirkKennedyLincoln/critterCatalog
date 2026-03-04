package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet saved = petService.savePet(petDTO);

        PetDTO result = new PetDTO();
        result.setBirthDate(saved.getBirthday());
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setNotes(saved.getNotes());
        result.setOwnerId(saved.getOwnerId());
        result.setType(saved.getPetType());

        return result;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        PetDTO dto = new PetDTO();

        dto.setBirthDate(pet.getBirthday());
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setNotes(pet.getNotes());
        dto.setOwnerId(pet.getOwnerId());
        dto.setType(pet.getPetType());

        return dto;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> group = petService.getPets();
        List<PetDTO> pets = new ArrayList<>();
        for (Pet pet : group) {
            PetDTO dto = new PetDTO();
            dto.setBirthDate(pet.getBirthday());
            dto.setId(pet.getId());
            dto.setName(pet.getName());
            dto.setNotes(pet.getNotes());
            dto.setOwnerId(pet.getOwnerId());
            dto.setType(pet.getPetType());

            pets.add(dto);
        }

        return pets;
    }


    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> group = petService.getPetsByOwner(ownerId);
        List<PetDTO> pets = new ArrayList<>();
        for (Pet pet : group) {
            PetDTO dto = new PetDTO();
            dto.setBirthDate(pet.getBirthday());
            dto.setId(pet.getId());
            dto.setName(pet.getName());
            dto.setNotes(pet.getNotes());
            dto.setOwnerId(pet.getOwnerId());
            dto.setType(pet.getPetType());

            pets.add(dto);
        }

        return pets;
    }
}
