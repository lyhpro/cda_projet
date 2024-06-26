package com.cdaprojet.gestion_personnel.model.employeeModel.employee;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeForm {
    
    private long id;
    private String secondname;
    private String firstname;
    private String placeOfBirth;
    private LocalDate dateOfBirth;

    private String email;
    private String address;
    private int postalCode;
    private String city;
    private int homenumber;
    private int phonenumber;

    private String post;
    private LocalDate dateOfHiring;
    private LocalDate dateEndOfContract;
    private long salary;
    private long hoursPerWeekId;
    private long contractId;
    private long departmentId;
}
