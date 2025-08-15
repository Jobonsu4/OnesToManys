package com.example.dogshelter.service;

import com.example.dogshelter.entity.Dogshelter;
import com.example.dogshelter.repository.DogShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    private final DogShelterRepository shelterRepository;

    public ShelterService(DogShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public List<Dogshelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public Optional<Dogshelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    public Dogshelter saveShelter(Dogshelter shelter) {
        return shelterRepository.save(shelter);
    }

    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id);
    }
}

