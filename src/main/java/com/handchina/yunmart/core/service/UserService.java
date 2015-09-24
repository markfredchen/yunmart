package com.handchina.yunmart.core.service;

import com.handchina.yunmart.constant.Constants;
import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.domain.secuirty.ForgetPasswordToken;
import com.handchina.yunmart.core.enumeration.UserStatus;
import com.handchina.yunmart.core.exception.SecurityViolationException;
import com.handchina.yunmart.core.exception.UnauthenticatedException;
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
import java.util.*;

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


    public User registerUser(User user) {
        user.setUserOID(UUID.randomUUID());
        user.setStatus(UserStatus.UNAUTHORIZED);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccount(accountRepository.findOneByName(Constants.PUBLIC_ACCOUNT));
        user.setRights(new HashSet<>(authorityRepository.findByNameIn(new HashSet<>(Arrays.asList(Constants.ROLE_USER)))));
        userRepository.save(user);
        return user;
    }

    public void updateProfile(User user) {
        userRepository.findByEmail(SecurityUtils.getCurrentLogin()).ifPresent(u -> {
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setCompanyName(user.getCompanyName());
            u.setMobile(user.getMobile());
            userRepository.save(u);
        });
    }

    public void changePassword(String password) {
        userRepository.findByEmail(SecurityUtils.getCurrentLogin()).ifPresent(u -> {
            String encryptedPassword = passwordEncoder.encode(password);
            u.setPassword(encryptedPassword);
            userRepository.save(u);
        });
    }

    public User getCurrentUser() {
        String login = SecurityUtils.getCurrentLogin();
        if(login.equals("anonymousUser")) {
            throw new UnauthenticatedException();
        }
        User currentUser = userRepository.findByEmail(SecurityUtils.getCurrentLogin()).get();
        currentUser.getRights().size();
        return currentUser;
    }

    public ForgetPasswordToken getForgetPasswordToken(String email) {
        User userToGetToken = null;
        Optional<User> userByUsername = userRepository.findByEmail(email);
        if (userByUsername.isPresent()) {
            userToGetToken = userByUsername.get();
        } else {
            throw new ValidationException(new ValidationError("email", "user.not.exists"));
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

    public void migrateToCompany(User user, String companyName) {

    }

}
