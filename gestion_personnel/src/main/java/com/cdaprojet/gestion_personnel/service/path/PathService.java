package com.cdaprojet.gestion_personnel.service.path;

import java.util.List;

import com.cdaprojet.gestion_personnel.model.pathModel.path.PathDto;

public interface PathService {
    
    List<PathDto> getPathsByRoleName(String roleName);

}
