package com.example.TodoList.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private AppUser appUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(appUser.getAuthority()));
    }

    public AppUser getUser() {
        return appUser;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    // is account still valid
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // is account not locked
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // are credentials valid
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // is account enabled. Only enable when otp is correct
    public boolean isEnabled() {
        return appUser.isEnabled();
//        return true;
    }

}
