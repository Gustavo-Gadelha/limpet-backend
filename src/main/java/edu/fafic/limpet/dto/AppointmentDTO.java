package edu.fafic.limpet.dto;

import edu.fafic.limpet.enums.AppointmentStatus;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AppointmentDTO {
    private UUID id;
    private AppointmentStatus status;
    private Date date;
    private String remarks;
    private UUID petId;
    private UUID clientId;
}
