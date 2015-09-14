package com.handchina.yunmart.core.security;

import com.handchina.yunmart.core.domain.Authority;
import com.handchina.yunmart.core.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by markfredchen on 9/13/15.
 */
public class Principal extends User implements UserDetails {

    public Principal(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.userOID = user.getUserOID();
        this.userID = user.getUserID();
        this.title = user.getTitle();
        this.rights = user.getRights();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRights().stream().map(right -> new SimpleGrantedAuthority(right.getName())).collect(Collectors.toList());
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
