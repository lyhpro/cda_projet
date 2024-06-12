package com.cdaprojet.gestion_personnel.model.contactDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactDetailDto {
    
    private long id;
    private String email;
    private String address;
    private int postalCode;
    private String city;
    private int homenumber;
    private int phonenumber;
}
