package com.cdaprojet.gestion_personnel.model.pathModel.path;

import java.util.List;

import com.cdaprojet.gestion_personnel.model.pathModel.pathAssigned.PathAssigned;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "paths")
public class Path {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "urlpath")
    private String urlPath;

    private String alias;

    private boolean enable;

    @JsonIgnore
    @OneToMany(mappedBy = "path")
    private List<PathAssigned> listPathAssigned;

    /**
     * Constructeur appelé pour l'initialisation de route dans la bdd
     * @param nom
     * @param chemin
     */
    public Path(String name, String urlPath, String alias) {
        this.id = 0;
        this.name = name;
        this.urlPath = urlPath;
        this.alias = alias;
        this.enable = true;
    }
}
