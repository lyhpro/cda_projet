package com.cdaprojet.gestion_personnel.model.employee;

import java.time.LocalDate;
import java.util.List;

import com.cdaprojet.gestion_personnel.model.contactDetail.ContactDetail;
import com.cdaprojet.gestion_personnel.model.holliday.Holliday;
import com.cdaprojet.gestion_personnel.model.professionalDetail.ProfessionalDetail;
import com.cdaprojet.gestion_personnel.model.recording.Recording;
import com.cdaprojet.gestion_personnel.model.rtt.Rtt;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    private LocalDate dateOfBirth;

    private boolean enable;

    private LocalDate dateOfCreation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_detail_id", referencedColumnName = "id")
    private ContactDetail contactDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professional_detail_id", referencedColumnName = "id")
    private ProfessionalDetail professionalDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Holliday> hollidays; 

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Rtt> rtts; 

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Recording> recordings; 

}
