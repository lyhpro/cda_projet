package com.cdaprojet.gestion_personnel.service.specialDay.rtt;

import java.sql.Timestamp;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.employeeModel.employee.Employee;
import com.cdaprojet.gestion_personnel.model.specialDay.rtt.Rtt;
import com.cdaprojet.gestion_personnel.repository.EmployeeRepository;
import com.cdaprojet.gestion_personnel.repository.RttRepository;

@Service
public class RttServiceImpl implements RttService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RttRepository rttRepository;

    @Override
    public void add(long employeeId, int nbDay) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);   
        Rtt lastRtt = employee.getRtts()
            .stream()
            .max(Comparator.comparing(Rtt::getDate))
            .orElse(null);
        int updatedNbDay = lastRtt.getNbDay() + nbDay;
        Rtt newRtt = new Rtt(0, updatedNbDay, new Timestamp(System.currentTimeMillis()), employee);
        rttRepository.save(newRtt);
    }

    @Override
    public void remove(long employeeId, int nbDay) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);   
        Rtt lastRtt = employee.getRtts()
            .stream()
            .max(Comparator.comparing(Rtt::getDate))
            .orElse(null);
        int updatedNbDay = lastRtt.getNbDay() - nbDay;
        Rtt newRtt = new Rtt(0, updatedNbDay, new Timestamp(System.currentTimeMillis()), employee);
        rttRepository.save(newRtt);
    }

}
