package com.cdaprojet.gestion_personnel.service.publicHoliday;

import java.time.LocalDate;
import java.util.List;

public interface PublicHolidayService {
    
    List<LocalDate> publicHolidaysOfTheYear(int year);
    
}
