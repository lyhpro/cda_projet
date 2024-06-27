package com.cdaprojet.gestion_personnel.service.specialDay.holiday;

public interface HolidayService {
    void add(long employeeId, int nbDay);
    void remove(long employeeId, int nbDay);
}
