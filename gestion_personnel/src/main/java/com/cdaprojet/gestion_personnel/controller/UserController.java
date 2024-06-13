package com.cdaprojet.gestion_personnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdaprojet.gestion_personnel.model.employee.EmployeeDto;
import com.cdaprojet.gestion_personnel.model.employee.EmployeeForm;
import com.cdaprojet.gestion_personnel.model.recording.RecordingDto;
import com.cdaprojet.gestion_personnel.service.employee.EmployeeService;
import com.cdaprojet.gestion_personnel.service.recording.RecordingService;

@RestController
@RequestMapping("/gestionnaire-personnel/user")
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RecordingService recordingService;
    
    @GetMapping("/hello")
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("Hello User");
    }

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

    @PostMapping("/createRecording")
    public ResponseEntity<RecordingDto> createRecording(@RequestBody RecordingDto recordingDto) {
        return ResponseEntity.ok(recordingService.create(recordingDto));
    }

    @GetMapping("/getRecordings/{id}")
    public ResponseEntity<List<RecordingDto>> getRecordingsByEmployeeId(@PathVariable long id) {
        return ResponseEntity.ok(recordingService.getRecordingsByEmployeeId(id));
    }

}
