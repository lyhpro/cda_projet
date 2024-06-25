package com.cdaprojet.gestion_personnel.service.publicHoliday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {
    
    @Override
    public List<LocalDate> publicHolidaysOfTheYear(int year) {
        List<LocalDate> publicHollidays = new ArrayList<LocalDate>();
        List<LocalDate> varyPublicHollidays = new ArrayList<LocalDate>();
        
        LocalDate nouvelAn = LocalDate.of(year,1,1);
        publicHollidays.add(nouvelAn);

        LocalDate feteDuTravail = LocalDate.of(year,5,1);
        publicHollidays.add(feteDuTravail);

        LocalDate victoire1945 = LocalDate.of(year,5,8);
        publicHollidays.add(victoire1945);     
        
        LocalDate feteNationale = LocalDate.of(year,7,14);
        publicHollidays.add(feteNationale);

        LocalDate assomption = LocalDate.of(year,8,15);
        publicHollidays.add(assomption);

        LocalDate toussaint = LocalDate.of(year,11,1);
        publicHollidays.add(toussaint);

        LocalDate armistice1918 = LocalDate.of(year,11,1);
        publicHollidays.add(armistice1918);

        LocalDate noel = LocalDate.of(year,12,25);
        publicHollidays.add(noel);
        
        varyPublicHollidays = calculateDateOfVaryPublicHoliday(year);
        publicHollidays.addAll(varyPublicHollidays);

        return publicHollidays;
    }

    public List<LocalDate> calculateDateOfVaryPublicHoliday(int year) {
        List<LocalDate> varyPublicHollidays = new ArrayList<LocalDate>();

        LocalDate easterSunday = calculateEasterSunday(year);
        varyPublicHollidays.add(easterSunday);

        LocalDate easterMonday = easterSunday.plusDays(1);
        varyPublicHollidays.add(easterMonday);

        LocalDate ascensionDay = easterSunday.plusDays(39);
        varyPublicHollidays.add(ascensionDay);

        LocalDate pentecostSunday = easterSunday.plusDays(49);
        varyPublicHollidays.add(pentecostSunday);

        LocalDate pentecostMonday = pentecostSunday.plusDays(1);
        varyPublicHollidays.add(pentecostMonday);

        return varyPublicHollidays;
    }

    public LocalDate calculateEasterSunday(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;
        return LocalDate.of(year, month, day);
    }
}
