package com.cdaprojet.gestion_personnel.model.employee;

import java.sql.Timestamp;

import com.cdaprojet.gestion_personnel.model.contactDetail.ContactDetail;
import com.cdaprojet.gestion_personnel.model.professionalDetail.ProfessionalDetail;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String secondname;

    private String firstname;

    private String placeOfBirth;

    private Timestamp dateOfBirth;

    private boolean enable;

    private Timestamp dateOfCreation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_detail_id", referencedColumnName = "id")
    private ContactDetail contactDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professional_detail_id", referencedColumnName = "id")
    private ProfessionalDetail professionalDetail;

}