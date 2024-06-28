package com.cdaprojet.gestion_personnel.service.specialDay.illness;

import java.sql.Timestamp;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.employeeModel.employee.Employee;
import com.cdaprojet.gestion_personnel.model.specialDay.illness.Illness;
import com.cdaprojet.gestion_personnel.repository.EmployeeRepository;
import com.cdaprojet.gestion_personnel.repository.IllnessRepository;

@Service
public class IllnessServiceImpl implements IllnessService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IllnessRepository illnessRepository;

    @Override
    public void add(long employeeId, int nbDay) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);   
        Illness lastIllness = employee.getIllnesses()
            .stream()
            .max(Comparator.comparing(Illness::getDate))
            .orElse(null);
        int updatedNbDay = lastIllness.getNbDay() + nbDay;
        Illness newIllness = new Illness(0, updatedNbDay, new Timestamp(System.currentTimeMillis()), employee);
        illnessRepository.save(newIllness);
    }

    @Override
    public void remove(long employeeId, int nbDay) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);   
        Illness lastIllness = employee.getIllnesses()
            .stream()
            .max(Comparator.comparing(Illness::getDate))
            .orElse(null);
        int updatedNbDay = lastIllness.getNbDay() - nbDay;
        Illness newIllness = new Illness(0, updatedNbDay, new Timestamp(System.currentTimeMillis()), employee);
        illnessRepository.save(newIllness);
    }

}
