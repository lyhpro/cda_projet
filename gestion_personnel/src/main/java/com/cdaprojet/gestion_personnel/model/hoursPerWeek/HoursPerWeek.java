package com.cdaprojet.gestion_personnel.model.hoursPerWeek;

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
@Table(name = "hours_per_week")
public class HoursPerWeek {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int hours;
    private boolean enable;

    @JsonIgnore
    @OneToMany(mappedBy = "hoursPerWeek")
    List<ProfessionalDetail> professionalDetails;
}
