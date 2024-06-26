package com.cdaprojet.gestion_personnel.service.path;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.pathModel.path.Path;
import com.cdaprojet.gestion_personnel.model.pathModel.path.PathDto;
import com.cdaprojet.gestion_personnel.model.pathModel.pathAssigned.PathAssigned;
import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.repository.PathAssignedRepository;
import com.cdaprojet.gestion_personnel.repository.PathRepository;
import com.cdaprojet.gestion_personnel.repository.RoleRepository;

@Service
public class PathServiceImpl implements PathService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PathAssignedRepository pathAssignedRepository;

    @Autowired
    private PathRepository pathRepository;

    @Override
    public List<PathDto> getPathsByRoleName(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if(role.getId() != 0) {
            long idRole = role.getId();
            return this.getPathsByRoleId(idRole).stream().map(PathDto::new).toList();
        }
        return new ArrayList<PathDto>();
    }

    public List<Path> getPathsByRoleId(long roleId) {
        List<PathAssigned> listPathAssigned = pathAssignedRepository.findAllByRoleIdAndEnable(roleId,true);
        List<Path> listPath = new ArrayList<Path>();

        for(PathAssigned pa: listPathAssigned) {
            Path path = this.pathRepository.findById(pa.getPath().getId()).orElse(null);
            if(path != null) {
                listPath.add(path);
            }
        }

        return listPath;
    }

}
