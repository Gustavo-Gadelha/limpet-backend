package edu.fafic.limpet.service;

import edu.fafic.limpet.exception.NotFoundException;
import edu.fafic.limpet.model.Client;
import edu.fafic.limpet.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found"));
    }

    public void delete(UUID id) {
        clientRepository.deleteById(id);
    }
}
