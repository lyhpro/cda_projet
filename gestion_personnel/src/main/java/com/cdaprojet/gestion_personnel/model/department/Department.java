package com.cdaprojet.gestion_personnel.model.department;

import java.util.List;

import com.cdaprojet.gestion_personnel.model.employeeModel.professionalDetail.ProfessionalDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "departments")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private boolean enable;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<ProfessionalDetail> professionalDetails; 

}
