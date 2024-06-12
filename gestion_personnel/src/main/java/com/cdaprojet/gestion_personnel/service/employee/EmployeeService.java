package com.cdaprojet.gestion_personnel.service.employee;

import java.util.List;

import com.cdaprojet.gestion_personnel.model.employee.EmployeeDto;
import com.cdaprojet.gestion_personnel.model.employee.EmployeeForm;

public interface EmployeeService {

    EmployeeDto create(EmployeeForm employeeForm);
    EmployeeDto update(EmployeeForm employeeForm);
    EmployeeDto delete(long id);
    EmployeeDto getEmployeeById(long id);
    List<EmployeeDto> getAllEmployee();

}
