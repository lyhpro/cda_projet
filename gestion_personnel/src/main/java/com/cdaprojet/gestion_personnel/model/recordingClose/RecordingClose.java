package com.cdaprojet.gestion_personnel.model.recordingClose;

import java.time.Duration;
import java.time.LocalDate;

import com.cdaprojet.gestion_personnel.model.employee.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "recordings_close")
public class RecordingClose {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private boolean newEmployee;
    private Duration totalHours;
    private Duration extraHours;
    private Duration dueHours;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;
    
}
