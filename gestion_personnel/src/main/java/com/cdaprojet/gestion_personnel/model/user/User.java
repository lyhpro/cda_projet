package com.cdaprojet.gestion_personnel.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.model.userProfilActivationToken.UserProfilActivationToken;
import com.cdaprojet.gestion_personnel.model.userPwdActivationToken.UserPwdActivationToken;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String secondname;

    private String firstname;

    private String email;

    private String password;

    private boolean enable;

    private boolean passwordUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id", updatable = true)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserProfilActivationToken> userProfilActivationTokens;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserPwdActivationToken> userPwdActivationTokens;

    public User(String secondName, String firstName, String email, String password, Role role, boolean enable, boolean passwordUpdated) {
        this.id = 0;
        this.secondname = secondName;
        this.firstname = firstName;
        this.email = email;
        this.password = password;
        this.enable = enable;
        this.passwordUpdate = passwordUpdated;
        this.role = role;
        this.userProfilActivationTokens = new ArrayList<UserProfilActivationToken>();
        this.userPwdActivationTokens = new ArrayList<UserPwdActivationToken>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

}
