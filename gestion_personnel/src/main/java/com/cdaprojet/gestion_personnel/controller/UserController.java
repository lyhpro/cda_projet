package com.cdaprojet.gestion_personnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdaprojet.gestion_personnel.model.contractType.ContractType;
import com.cdaprojet.gestion_personnel.model.dayType.DayType;
import com.cdaprojet.gestion_personnel.model.department.Department;
import com.cdaprojet.gestion_personnel.model.employee.EmployeeDto;
import com.cdaprojet.gestion_personnel.model.employee.EmployeeForm;
import com.cdaprojet.gestion_personnel.model.hoursPerWeek.HoursPerWeek;
import com.cdaprojet.gestion_personnel.model.month.Month;
import com.cdaprojet.gestion_personnel.model.recording.RecordingDto;
import com.cdaprojet.gestion_personnel.service.contractType.ContractTypeService;
import com.cdaprojet.gestion_personnel.service.dayType.DayTypeService;
import com.cdaprojet.gestion_personnel.service.department.DepartmentService;
import com.cdaprojet.gestion_personnel.service.employee.EmployeeService;
import com.cdaprojet.gestion_personnel.service.hoursPerWeek.HoursPerWeekService;
import com.cdaprojet.gestion_personnel.service.month.MonthService;
import com.cdaprojet.gestion_personnel.service.recording.RecordingService;

@RestController
@RequestMapping("/gestionnaire-personnel/user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RecordingService recordingService;

    @Autowired
    private ContractTypeService contractTypeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DayTypeService dayTypeService;

    @Autowired
    private HoursPerWeekService hoursPerWeekService;

    @Autowired
    private MonthService monthService;
    
    @GetMapping("/hello")
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("Hello User");
    }

    // EMPLOYEES ///////////////////////////////////////////////////////////////////////////

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeForm employeeForm) {
        return ResponseEntity.ok(employeeService.create(employeeForm));
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeForm employeeForm) {
        return ResponseEntity.ok(employeeService.update(employeeForm));
    }

    @GetMapping("/deleteEmployee/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable long id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    // RECORDINGS ///////////////////////////////////////////////////////////////////////////

    @PostMapping("/createRecording")
    public ResponseEntity<Void> createRecording(@RequestBody RecordingDto recordingDto) {
        recordingService.create(recordingDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllEmployeeRecording/{employeeId}/{year}/{monthId}")
    public ResponseEntity<List<RecordingDto>> getAllEmployeeRecording(@PathVariable long employeeId, @PathVariable long year, @PathVariable long monthId) {
        return ResponseEntity.ok(recordingService.getAllEmployeeRecording(employeeId, year, monthId));
    }

    // CONTRACTTYPES ///////////////////////////////////////////////////////////////////////////

    @GetMapping("/getAllContractType")
    public ResponseEntity<List<ContractType>> getAllContractType() {
        return ResponseEntity.ok(contractTypeService.getAllContractType());
    }

    // DEPARTMENTS ///////////////////////////////////////////////////////////////////////////

    @GetMapping("/getAllDepartment")
    public ResponseEntity<List<Department>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    // DAYTYPES ///////////////////////////////////////////////////////////////////////////

    @GetMapping("getAllDayType")
    public ResponseEntity<List<DayType>> getAllDayType() {
        return ResponseEntity.ok(dayTypeService.getAllDayType());
    }

    // HOURSPERWEEK ///////////////////////////////////////////////////////////////////////////

    @GetMapping("getAllHoursPerWeek")
    public ResponseEntity<List<HoursPerWeek>> getAllHoursPerWeek() {
        return ResponseEntity.ok(hoursPerWeekService.getAllHoursPerWeek());
    }

    // MONTH ///////////////////////////////////////////////////////////////////////////

    @GetMapping("getAllMonth")
    public ResponseEntity<List<Month>> getAllMonth() {
        return ResponseEntity.ok(monthService.getAllMonth());
    }
}
