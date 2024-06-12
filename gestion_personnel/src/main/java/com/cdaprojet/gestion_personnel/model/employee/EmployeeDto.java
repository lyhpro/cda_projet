package com.cdaprojet.gestion_personnel.model.employee;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    
    private long id;
    private String secondname;
    private String firstname;
    private String placeOfBirth;
    private Timestamp dateOfBirth;
    private boolean enable;
    private Timestamp dateOfCreation;
    private long contactDetailId;
    private long professionalDetailId;
    
    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.secondname = employee.getSecondname();
        this.firstname = employee.getFirstname();
        this.placeOfBirth = employee.getPlaceOfBirth();
        this.dateOfBirth = employee.getDateOfBirth();
        this.enable = employee.isEnable();
        this.dateOfCreation = employee.getDateOfCreation();
        this.contactDetailId = employee.getContactDetail().getId();
        this.professionalDetailId = employee.getProfessionalDetail().getId();
    }
}
