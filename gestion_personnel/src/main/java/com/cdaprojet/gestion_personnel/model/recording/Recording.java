package com.cdaprojet.gestion_personnel.model.recording;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import com.cdaprojet.gestion_personnel.model.dayType.DayType;
import com.cdaprojet.gestion_personnel.model.employeeModel.employee.Employee;

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
@Table(name = "recordings")
public class Recording {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private LocalDate dateStart;
    private LocalDate dateStop;
    private LocalTime hourStart;
    private LocalTime hourStop;
    private LocalTime breakStart;
    private LocalTime breakStop;
    private Duration extraHours;
    private Duration dueHours;
    private Duration totalHours;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "day_type_id", nullable = true)
    private DayType dayType;
}
