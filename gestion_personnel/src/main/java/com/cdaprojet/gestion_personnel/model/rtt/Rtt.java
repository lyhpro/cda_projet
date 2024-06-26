package com.cdaprojet.gestion_personnel.model.rtt;

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
@Table(name = "rtts")
public class Rtt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int year;

    private int nbDay;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;
    
}
