package com.handchina.yunmart.core.service;

import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.domain.secuirty.ForgetPasswordToken;
import com.handchina.yunmart.core.exception.ValidationError;
import com.handchina.yunmart.core.exception.ValidationException;
import com.handchina.yunmart.core.persistence.AccountRepository;
import com.handchina.yunmart.core.persistence.AuthorityRepository;
import com.handchina.yunmart.core.persistence.PersistentTokenRepository;
import com.handchina.yunmart.core.persistence.UserRepository;
import com.handchina.yunmart.core.persistence.security.ForgetPasswordTokenRepository;
import com.handchina.yunmart.core.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
    private ForgetPasswordTokenRepository forgetPasswordTokenRepository;

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

    public ForgetPasswordToken getForgetPasswordToken(String usernameOrEmail) {
        User userToGetToken = null;
        Optional<User> userByUsername = userRepository.findByUsername(usernameOrEmail);
        if (userByUsername.isPresent()) {
            userToGetToken = userByUsername.get();
        } else {
            Optional<User> userByEmail = userRepository.findByEmail(usernameOrEmail);
            if (userByEmail.isPresent()) {
                userToGetToken = userByEmail.get();
            } else {
                throw new ValidationException(new ValidationError("usernameOrEmail", "user.not.exists"));
            }
        }
        ForgetPasswordToken token = new ForgetPasswordToken();
        token.setForgetPasswordToken(UUID.randomUUID());
        token.setUser(userToGetToken);
        return forgetPasswordTokenRepository.save(token);
    }

    public void resetPassword(UUID forgetPasswordToken, String newPassword) {
        ForgetPasswordToken token = forgetPasswordTokenRepository.findOne(forgetPasswordToken);
        if (token != null) {
            User user = token.getUser();
            user.setPassword(newPassword);
            userRepository.save(user);
        } else {
            throw new ValidationException(new ValidationError("forgetPasswordToken", "not.exists"));
        }
    }

}
