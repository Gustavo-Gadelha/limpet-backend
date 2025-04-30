package edu.fafic.limpet.controller;

import edu.fafic.limpet.dto.ClientDTO;
import edu.fafic.limpet.mapper.ClientMapper;
import edu.fafic.limpet.model.Client;
import edu.fafic.limpet.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> clients = clientService.findAll().stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable UUID id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok(clientMapper.toDTO(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable UUID id, @Valid @RequestBody ClientDTO clientDTO) {
        Client current = clientService.findById(id);
        Client updated = clientMapper.toEntity(clientDTO);
        updated.setId(current.getId());

        Client saved = clientService.save(updated);
        return ResponseEntity.ok(clientMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
