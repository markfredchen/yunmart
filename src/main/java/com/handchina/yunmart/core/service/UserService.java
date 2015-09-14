package com.handchina.yunmart.core.service;

import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.persistence.AccountRepository;
import com.handchina.yunmart.core.persistence.AuthorityRepository;
import com.handchina.yunmart.core.persistence.PersistentTokenRepository;
import com.handchina.yunmart.core.persistence.UserRepository;
import com.handchina.yunmart.core.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by markfredchen on 9/13/15.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public User createUser(User user, String accountName, List<String> authorities) {
        user.setUserOID(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccount(accountRepository.findOneByName(accountName));
        user.setRights(new HashSet<>(authorityRepository.findByNameIn(authorities)));
        userRepository.save(user);
        return user;
    }

    public User getCurrentUser() {
        User currentUser = userRepository.findByUsername(SecurityUtils.getCurrentLogin()).get();
        currentUser.getRights().size();
        return currentUser;
    }
}
