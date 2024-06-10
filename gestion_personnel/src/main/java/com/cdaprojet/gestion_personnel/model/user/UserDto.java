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
    private String email;
    private String password;
    private boolean enable;
    private long roleId;

    public UserDto(User user) {
        this.id = user.getId();
        this.secondname = user.getSecondname();
        this.firstname = user.getFirstname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enable = user.isEnable();
        this.roleId = user.getRole().getId();
    }

}
