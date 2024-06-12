package com.cdaprojet.gestion_personnel.model.professionalDetail;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessionalDetailDto {
    
    private long id;
    private String post;
    private Timestamp dateOfHiring;
    private Timestamp dateEndOfContract;
    private long salary;
    private long hoursPerWeek;
    private long contractTypeId;
    private long departmentId;
    
}