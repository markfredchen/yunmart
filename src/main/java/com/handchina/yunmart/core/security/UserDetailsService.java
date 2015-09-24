package com.handchina.yunmart.core.security;

import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by markfredchen on 9/13/15.
 */
@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String login = username.toLowerCase();
        Optional<User> userFromDB = userRepository.findByEmail(login);

        return userFromDB.map(user -> {
            List<GrantedAuthority> grantedAuthorities = user.getRights().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(login,
                user.getPassword(),
                grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
    }
}
