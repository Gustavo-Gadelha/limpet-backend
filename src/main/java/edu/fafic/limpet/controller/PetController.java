package edu.fafic.limpet.controller;

import edu.fafic.limpet.dto.PetDTO;
import edu.fafic.limpet.mapper.PetMapper;
import edu.fafic.limpet.model.Pet;
import edu.fafic.limpet.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    @GetMapping
    public ResponseEntity<List<PetDTO>> findAll() {
        List<PetDTO> pets = petService.findAll().stream()
                .map(petMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable UUID id) {
        Pet pet = petService.findById(id);
        return ResponseEntity.ok(petMapper.toDTO(pet));
    }

    @PostMapping
    public ResponseEntity<PetDTO> create(@Valid @RequestBody PetDTO petDTO) {
        Pet saved = petService.save(petMapper.toEntity(petDTO));
        return ResponseEntity.ok(petMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> update(@PathVariable UUID id, @Valid @RequestBody PetDTO petDTO) {
        Pet current = petService.findById(id);
        Pet updated = petMapper.toEntity(petDTO);
        updated.setId(current.getId());

        Pet saved = petService.save(updated);
        return ResponseEntity.ok(petMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
