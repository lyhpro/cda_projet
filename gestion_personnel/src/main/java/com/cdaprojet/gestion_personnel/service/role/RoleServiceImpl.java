package com.cdaprojet.gestion_personnel.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll().stream().filter(role -> role.isEnable()).toList();
    }
}
