package com.cdaprojet.gestion_personnel.model.pathModel.pathAssigned;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PathAssignedDto {
    
    private long id;
    private long pathId;
    private long roleId;
    private boolean enable;

    /**
     * Constructeur utilisé pour envoyé un PathAssigned du back au front
     * @param pathAssigned Le PathAssigned issue du back
     */
    public PathAssignedDto(PathAssigned pathAssigned) {
        this.id = pathAssigned.getId();
        this.pathId = pathAssigned.getPath().getId();
        this.roleId = pathAssigned.getRole().getId();
        this.enable = pathAssigned.isEnable();
    }
}
