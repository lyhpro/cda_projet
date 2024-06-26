package com.cdaprojet.gestion_personnel.model.employeeModel.professionalDetail;

import java.time.LocalDate;

import com.cdaprojet.gestion_personnel.model.contractType.ContractType;
import com.cdaprojet.gestion_personnel.model.department.Department;
import com.cdaprojet.gestion_personnel.model.hoursPerWeek.HoursPerWeek;

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
@Table(name = "professional_details")
public class ProfessionalDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String post;
    private LocalDate dateOfHiring;
    private LocalDate dateEndOfContract;
    private float salary;

    @ManyToOne
    @JoinColumn(name = "hoursperweek_id", nullable = true)
    private HoursPerWeek hoursPerWeek;

    @ManyToOne
    @JoinColumn(name = "contract_type_id", nullable = true)
    private ContractType contractType;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

}
