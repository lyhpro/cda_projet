package com.cdaprojet.gestion_personnel.model.contractType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContractTypeDto {
    
    private long id;
    private String name;
    private boolean enabled;

}
