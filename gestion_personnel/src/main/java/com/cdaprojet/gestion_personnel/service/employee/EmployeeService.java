package com.cdaprojet.gestion_personnel.service.employee;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.cdaprojet.gestion_personnel.model.employeeModel.employee.EmployeeDto;
import com.cdaprojet.gestion_personnel.model.employeeModel.employee.EmployeeForm;

public interface EmployeeService {

    EmployeeDto create(EmployeeForm employeeForm);
    EmployeeDto update(EmployeeForm employeeForm);
    EmployeeDto delete(long id);
    EmployeeDto getEmployeeById(long id);
    List<EmployeeDto> getAllEmployee();
    
    Map<String,List<Integer>> getEmployeeNbHolidaysRttsIllnesses(long employeeId, long yearId, long monthId);
    void addDaysToEmployeeSpecialDay(long employeeId, String dayname, int nbDay);
    Map<String,List<Duration>> getEmployeeWorkingHours(long employeeId, long yearId, long monthId);

}
