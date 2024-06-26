package com.cdaprojet.gestion_personnel.model.pathModel.path;

import java.util.List;

import com.cdaprojet.gestion_personnel.model.pathModel.pathAssigned.PathAssigned;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PathDto {
    
    private long id;
    private String name;
    private String urlPath;
    private String alias;
    private boolean enable;
    private List<Long> listIdPathAssigned;

    /**
     * Constructeur utilisé pour envoyer un Path du back au front
     * @param path Le Path issue du back
     */
    public PathDto(Path path) {
        this.id = path.getId();
        this.name = path.getName();
        this.urlPath = path.getUrlPath();
        this.alias = path.getAlias();
        this.enable = path.isEnable();
        this.listIdPathAssigned = path.getListPathAssigned().stream().map(PathAssigned::getId).toList();
    }
}
