package com.cdaprojet.gestion_personnel.model.dayType;

import java.util.List;

import com.cdaprojet.gestion_personnel.model.recording.Recording;
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
@Table(name = "day_types")
public class DayType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private boolean enable;

    @JsonIgnore
    @OneToMany(mappedBy = "dayType")
    private List<Recording> recordings; 

}
