package edu.fafic.limpet.dto;

import edu.fafic.limpet.enums.Authority;
import lombok.Data;

import java.util.UUID;

@Data
public class ClientDTO {
    private UUID id;
    private String username;
    private String email;
    private Authority authority;
    private boolean isActive;
}
