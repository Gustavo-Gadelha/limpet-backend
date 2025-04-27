package edu.fafic.limpet.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClientDTO {
    private UUID id;
    private String username;
    private String email;
    private boolean isActive;
}
