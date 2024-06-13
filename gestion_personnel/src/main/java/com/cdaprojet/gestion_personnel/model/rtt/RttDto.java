package com.cdaprojet.gestion_personnel.model.rtt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RttDto {
    
    private long id;
    private int year;
    private int nbDay;
    private long employeeId;

    public RttDto(Rtt rtt) {
        this.id = rtt.getId();
        this.year = rtt.getYear();
        this.nbDay = rtt.getNbDay();
        this.employeeId = rtt.getEmployee().getId();
    }
}
