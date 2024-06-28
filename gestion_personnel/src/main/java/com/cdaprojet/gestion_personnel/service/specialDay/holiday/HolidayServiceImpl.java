package com.cdaprojet.gestion_personnel.service.specialDay.holiday;

import java.sql.Timestamp;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.employeeModel.employee.Employee;
import com.cdaprojet.gestion_personnel.model.specialDay.holiday.Holiday;
import com.cdaprojet.gestion_personnel.repository.EmployeeRepository;
import com.cdaprojet.gestion_personnel.repository.HolidayRepository;

@Service
public class HolidayServiceImpl implements HolidayService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public void add(long employeeId, int nbDay) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);   
        Holiday lastHoliday = employee.getHolidays()
            .stream()
            .max(Comparator.comparing(Holiday::getDate))
            .orElse(null);
        int updatedNbDay = lastHoliday.getNbDay() + nbDay;
        Holiday newHoliday = new Holiday(0, updatedNbDay, new Timestamp(System.currentTimeMillis()), employee);
        holidayRepository.save(newHoliday);
    }

    @Override
    public void remove(long employeeId, int nbDay) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);   
        Holiday lastHoliday = employee.getHolidays()
            .stream()
            .max(Comparator.comparing(Holiday::getDate))
            .orElse(null);
        int updatedNbDay = lastHoliday.getNbDay() - nbDay;
        Holiday newHoliday = new Holiday(0, updatedNbDay, new Timestamp(System.currentTimeMillis()), employee);
        holidayRepository.save(newHoliday);
    }
}
