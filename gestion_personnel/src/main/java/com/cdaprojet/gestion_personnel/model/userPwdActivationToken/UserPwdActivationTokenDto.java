package com.cdaprojet.gestion_personnel.model.userPwdActivationToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPwdActivationTokenDto {
    
    private long id;
    private String userpwdActivationToken;
    private long user_id;

    public UserPwdActivationTokenDto(UserPwdActivationToken userPwdActivationToken) {
        this.id = userPwdActivationToken.getId();
        this.userpwdActivationToken = userPwdActivationToken.getUserpwdActivationToken();
        this.user_id = userPwdActivationToken.getUser().getId();
    }

}
