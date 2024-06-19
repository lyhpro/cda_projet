package com.cdaprojet.gestion_personnel.service.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.department.Department;
import com.cdaprojet.gestion_personnel.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll().stream().filter(department -> department.isEnable()).toList();
    }

}
