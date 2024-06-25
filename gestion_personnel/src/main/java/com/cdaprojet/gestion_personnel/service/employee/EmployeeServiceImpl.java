package com.cdaprojet.gestion_personnel.service.employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.contactDetail.ContactDetail;
import com.cdaprojet.gestion_personnel.model.contractType.ContractType;
import com.cdaprojet.gestion_personnel.model.department.Department;
import com.cdaprojet.gestion_personnel.model.employee.Employee;
import com.cdaprojet.gestion_personnel.model.employee.EmployeeDto;
import com.cdaprojet.gestion_personnel.model.employee.EmployeeForm;
import com.cdaprojet.gestion_personnel.model.holiday.Holiday;
import com.cdaprojet.gestion_personnel.model.hoursPerWeek.HoursPerWeek;
import com.cdaprojet.gestion_personnel.model.professionalDetail.ProfessionalDetail;
import com.cdaprojet.gestion_personnel.model.recording.Recording;
import com.cdaprojet.gestion_personnel.model.rtt.Rtt;
import com.cdaprojet.gestion_personnel.repository.ContractTypeRepository;
import com.cdaprojet.gestion_personnel.repository.DepartmentRepository;
import com.cdaprojet.gestion_personnel.repository.EmployeeRepository;
import com.cdaprojet.gestion_personnel.repository.HoursPerWeekRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HoursPerWeekRepository hoursPerWeekRepository;

    @Override
    public EmployeeDto create(EmployeeForm employeeForm) {

        ContactDetail newContactDetail = new ContactDetail(
            0,
            employeeForm.getEmail(),
            employeeForm.getAddress(),
            employeeForm.getPostalCode(),
            employeeForm.getCity(),
            employeeForm.getHomenumber(),
            employeeForm.getPhonenumber()
        );

        Department department = departmentRepository.findById(employeeForm.getDepartmentId()).orElse(null);
        ContractType contractType = contractTypeRepository.findById(employeeForm.getContractId()).orElse(null);
        HoursPerWeek hoursPerWeek = hoursPerWeekRepository.findById(employeeForm.getHoursPerWeekId()).orElse(null);

        ProfessionalDetail newProfessionalDetail = new ProfessionalDetail(
            0,
            employeeForm.getPost(),
            employeeForm.getDateOfHiring(),
            employeeForm.getDateEndOfContract(),
            employeeForm.getSalary(),
            hoursPerWeek,
            contractType,
            department
        );

        Employee newEmployee = new Employee(
            0, 
            employeeForm.getSecondname(), 
            employeeForm.getFirstname(), 
            employeeForm.getPlaceOfBirth(), 
            employeeForm.getDateOfBirth(),
            true, 
            LocalDate.now(),
            newContactDetail, 
            newProfessionalDetail,
            new ArrayList<Holiday>(),
            new ArrayList<Rtt>(),
            new ArrayList<Recording>()
        );

        employeeRepository.save(newEmployee);

        return new EmployeeDto(newEmployee);
    }

    @Override
    public EmployeeDto update(EmployeeForm employeeForm) {
        Employee oldEmployee = employeeRepository.findById(employeeForm.getId()).orElse(null);
        
        ContactDetail oldContactDetail = oldEmployee.getContactDetail();
        ContactDetail updatedContactDetail = new ContactDetail(
            oldContactDetail.getId(), 
            employeeForm.getEmail(), 
            employeeForm.getAddress(), 
            employeeForm.getPostalCode(), 
            employeeForm.getCity(), 
            employeeForm.getHomenumber(), 
            employeeForm.getPhonenumber()
        );

        ContractType newContractType = contractTypeRepository.findById(employeeForm.getContractId()).orElse(null);
        Department newDepartment = departmentRepository.findById(employeeForm.getDepartmentId()).orElse(null);
        HoursPerWeek hoursPerWeek = hoursPerWeekRepository.findById(employeeForm.getHoursPerWeekId()).orElse(null);

        ProfessionalDetail oldProfessionalDetail = oldEmployee.getProfessionalDetail();
        ProfessionalDetail updateProfessionalDetail = new ProfessionalDetail(
            oldProfessionalDetail.getId(), 
            employeeForm.getPost(), 
            employeeForm.getDateOfHiring(), 
            employeeForm.getDateEndOfContract(), 
            employeeForm.getSalary(), 
            hoursPerWeek, 
            newContractType, 
            newDepartment
        );
        
        Employee updatedEmployee = new Employee(
            employeeForm.getId(), 
            employeeForm.getSecondname(), 
            employeeForm.getFirstname(), 
            employeeForm.getPlaceOfBirth(), 
            employeeForm.getDateOfBirth(), 
            oldEmployee.isEnable(), 
            LocalDate.now(), 
            updatedContactDetail, 
            updateProfessionalDetail,
            oldEmployee.getHollidays(),
            oldEmployee.getRtts(),
            oldEmployee.getRecordings()
        );

        employeeRepository.save(updatedEmployee);

        return new EmployeeDto(updatedEmployee);
    }

    @Override
    public EmployeeDto delete(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setEnable(false);
        employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {

        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee != null) {
            return new EmployeeDto(employee);
        }
        return null;

    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> allEmployee = employeeRepository.findAll();
        List<EmployeeDto> allEmployeeDto = allEmployee.stream().filter(employee -> employee.isEnable()).map(EmployeeDto::new).toList();
        return allEmployeeDto;
    }

}
