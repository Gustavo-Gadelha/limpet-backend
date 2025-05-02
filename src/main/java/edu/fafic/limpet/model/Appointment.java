package edu.fafic.limpet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String petName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String ddd;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = true)
    private String remarks;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
