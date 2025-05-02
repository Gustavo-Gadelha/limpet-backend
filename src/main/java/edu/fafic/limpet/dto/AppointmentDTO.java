package edu.fafic.limpet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class AppointmentDTO {

    private UUID id;

    @NotBlank(message = "Client name must not be blank")
    private String clientName;

    @NotBlank(message = "Pet name must not be blank")
    private String petName;

    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "DDD must not be blank")
    private String ddd;

    @NotBlank(message = "Phone number must not be blank")
    private String phoneNumber;

    @NotNull(message = "Date must not be blank")
    private LocalDate date;

    @NotNull(message = "Time must not be blank")
    private LocalTime time;

    private String remarks;
}
