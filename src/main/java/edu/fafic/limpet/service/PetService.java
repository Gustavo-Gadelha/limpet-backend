package edu.fafic.limpet.service;

import edu.fafic.limpet.exception.NotFoundException;
import edu.fafic.limpet.model.Pet;
import edu.fafic.limpet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(UUID id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pet not found"));
    }

    public void delete(UUID id) {
        petRepository.deleteById(id);
    }
}
