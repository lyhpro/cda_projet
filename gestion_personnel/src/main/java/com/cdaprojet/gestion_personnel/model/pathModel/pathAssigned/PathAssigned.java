package com.cdaprojet.gestion_personnel.model.pathModel.pathAssigned;

import com.cdaprojet.gestion_personnel.model.pathModel.path.Path;
import com.cdaprojet.gestion_personnel.model.role.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "paths_assigned")
public class PathAssigned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "path_id")
    private Path path;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private boolean enable;

    /**
     * Constructeur utilisé pour l'initialisation de la table route_attribuee dans la bdd
     * @param path
     * @param role
     */
    public PathAssigned(Path path, Role role) {
        this.id = 0;
        this.path = path;
        this.role = role;
        this.enable = true;
    }

}
