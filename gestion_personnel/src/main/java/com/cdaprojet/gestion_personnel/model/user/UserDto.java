package com.cdaprojet.gestion_personnel.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    
    private long id;
    private String secondname;
    private String firstname;
    private String roleName;
    private boolean enable;

    public UserDto(User user) {
        this.id = user.getId();
        this.secondname = user.getSecondname();
        this.firstname = user.getFirstname();
        this.roleName = user.getRole().getName();
        this.enable = user.isEnable();
    }

}
