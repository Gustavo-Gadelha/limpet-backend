package edu.fafic.limpet.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PetDTO {
    private UUID id;
    private String name;
    private UUID ownerId;
}
