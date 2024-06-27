package com.cdaprojet.gestion_personnel.service.specialDay.illness;

public interface IllnessService {
    void add(long employeeId, int nbDay);
    void remove(long employeeId, int nbDay);
}
