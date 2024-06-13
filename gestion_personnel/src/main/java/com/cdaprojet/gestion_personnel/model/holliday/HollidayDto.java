package com.cdaprojet.gestion_personnel.model.holliday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HollidayDto {
    
    private long id;
    private int year;
    private int nbDay;
    private long employeeId;

    public HollidayDto(Holliday holliday) {
        this.id = holliday.getId();
        this.year = holliday.getYear();
        this.nbDay = holliday.getNbDay();
        this.employeeId = holliday.getEmployee().getId();
    }

}
