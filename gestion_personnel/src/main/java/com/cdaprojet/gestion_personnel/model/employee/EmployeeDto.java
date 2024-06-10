package com.cdaprojet.gestion_personnel.model.employee;

import java.sql.Timestamp;
import java.util.List;

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
    private long professionnalDetailId;
    private List<Long> employeesId;
    
}
