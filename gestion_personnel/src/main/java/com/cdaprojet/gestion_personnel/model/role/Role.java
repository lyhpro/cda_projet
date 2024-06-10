package com.cdaprojet.gestion_personnel.model.role;

import java.util.ArrayList;
import java.util.List;

import com.cdaprojet.gestion_personnel.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private boolean enable;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<User> listUser;

    public Role() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.enable = false;
        this.listUser = new ArrayList<User>();
    }

    /**
     * Constructeur utilisé pour initialiser la table role dans la bdd
     * @param nom
     * @param description
     */
    public Role(String nom, String description) {
        this.id = 0;
        this.name = nom;
        this.description = description;
        this.enable = true;
        this.listUser = new ArrayList<User>();
    } 
    
}
