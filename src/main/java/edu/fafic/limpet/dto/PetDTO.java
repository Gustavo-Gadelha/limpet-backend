package edu.fafic.limpet.dto;

import edu.fafic.limpet.enums.Species;
import lombok.Data;

import java.util.UUID;

@Data
public class PetDTO {
    private UUID id;
    private String name;
    private Species species;
    private int weight;
    private String remarks;
    private UUID ownerId;
}
