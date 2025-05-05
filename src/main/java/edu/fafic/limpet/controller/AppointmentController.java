package edu.fafic.limpet.controller;

import edu.fafic.limpet.dto.AppointmentDTO;
import edu.fafic.limpet.email.EmailFormatter;
import edu.fafic.limpet.email.EmailService;
import edu.fafic.limpet.mapper.AppointmentMapper;
import edu.fafic.limpet.model.Appointment;
import edu.fafic.limpet.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;
    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll() {
        List<AppointmentDTO> appointments = appointmentService.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable UUID id) {
        Appointment appointment = appointmentService.findById(id);
        return ResponseEntity.ok(appointmentMapper.toDTO(appointment));
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@Valid @RequestBody AppointmentDTO appointmentDTO) {
        Appointment saved = appointmentService.save(appointmentMapper.toEntity(appointmentDTO));
        AppointmentDTO dto = appointmentMapper.toDTO(saved);

        String subject = "Confirmação de Agendamento";
        String body = EmailFormatter.confirmationEmail(dto);
        emailService.send(saved.getEmail(), subject, body);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(@PathVariable UUID id, @Valid @RequestBody AppointmentDTO appointmentDTO) {
        Appointment current = appointmentService.findById(id);
        Appointment updated = appointmentMapper.toEntity(appointmentDTO);
        updated.setId(current.getId());

        Appointment saved = appointmentService.save(updated);
        return ResponseEntity.ok(appointmentMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
