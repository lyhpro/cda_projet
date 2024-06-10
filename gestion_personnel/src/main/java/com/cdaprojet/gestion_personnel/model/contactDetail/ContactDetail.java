package com.cdaprojet.gestion_personnel.model.contactDetail;

import com.cdaprojet.gestion_personnel.model.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "contact_details")
public class ContactDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String address;

    private int postalCode;

    private String city;

    private int homenumber;

    private int phonenumber;

    @JsonIgnore
    @OneToOne(mappedBy = "contactDetail")
    private Employee employee;
    
}
