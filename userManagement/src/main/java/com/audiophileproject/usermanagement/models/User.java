package com.audiophileproject.usermanagement.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    @Column(unique = true)
    private String username;
    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    private String password;
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return  email;
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
}