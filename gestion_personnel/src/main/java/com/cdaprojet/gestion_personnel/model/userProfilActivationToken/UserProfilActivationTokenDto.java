package com.cdaprojet.gestion_personnel.model.userProfilActivationToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilActivationTokenDto {
    
    private long id;
    private String userprofilActivationToken;
    private long user_id;

    public UserProfilActivationTokenDto(UserProfilActivationToken userActivationToken) {
        this.id = userActivationToken.getId();
        this.userprofilActivationToken = userActivationToken.getUserprofilActivationToken();
        this.user_id = userActivationToken.getUser().getId();
    }
    
}
