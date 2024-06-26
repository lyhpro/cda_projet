package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.employeeModel.employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    
}
