package com.cdaprojet.gestion_personnel.model.employeeModel.employee;

import java.time.LocalDate;
import java.util.List;

import com.cdaprojet.gestion_personnel.model.employeeModel.contactDetail.ContactDetail;
import com.cdaprojet.gestion_personnel.model.employeeModel.professionalDetail.ProfessionalDetail;
import com.cdaprojet.gestion_personnel.model.recording.Recording;
import com.cdaprojet.gestion_personnel.model.specialDay.holiday.Holiday;
import com.cdaprojet.gestion_personnel.model.specialDay.illness.Illness;
import com.cdaprojet.gestion_personnel.model.specialDay.rtt.Rtt;
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
    private List<Holiday> holidays;
    
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Rtt> rtts;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Illness> illnesses;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Recording> recordings; 

}
