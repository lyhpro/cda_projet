package com.cdaprojet.gestion_personnel.model.employee;

import java.sql.Timestamp;

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
    private Timestamp dateOfBirth;

    private String email;
    private String address;
    private int postalCode;
    private String city;
    private int homenumber;
    private int phonenumber;

    private String post;
    private Timestamp dateOfHiring;
    private Timestamp dateEndOfContract;
    private long salary;
    private long hoursPerWeek;
    private long contractId;
    private long departmentId;
}
