package edu.fafic.limpet.dto;

import edu.fafic.limpet.enums.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AppointmentDTO {
    private UUID id;

    @NotBlank(message = "Status must not be blank")
    private AppointmentStatus status;

    @NotBlank(message = "Date must not be blank")
    private Date date;

    private String remarks;

    @NotBlank(message = "Pet id must not be blank")
    private UUID petId;

    @NotBlank(message = "Client id must not be blank")
    private UUID clientId;
}
