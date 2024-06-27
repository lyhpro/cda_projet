package com.cdaprojet.gestion_personnel.service.specialDay.rtt;

public interface RttService {
    void add(long employeeId, int nbDay);
    void remove(long employeeId, int nbDay);
}
