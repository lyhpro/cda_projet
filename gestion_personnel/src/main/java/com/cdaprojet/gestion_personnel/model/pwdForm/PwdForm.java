package com.cdaprojet.gestion_personnel.model.pwdForm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PwdForm {
    
    private long tokenId;
    private String password;
    private String confirmedPassword;

}
