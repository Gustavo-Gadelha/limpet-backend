package edu.fafic.limpet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class PetDTO {

    private UUID id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    private UUID ownerId;
}
