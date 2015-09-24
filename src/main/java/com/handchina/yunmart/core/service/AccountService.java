package com.handchina.yunmart.core.service;

import com.handchina.yunmart.constant.Constants;
import com.handchina.yunmart.core.domain.Account;
import com.handchina.yunmart.core.domain.Authority;
import com.handchina.yunmart.core.domain.Company;
import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.exception.ValidationError;
import com.handchina.yunmart.core.exception.ValidationException;
import com.handchina.yunmart.core.persistence.AccountRepository;
import com.handchina.yunmart.core.persistence.AuthorityRepository;
import com.handchina.yunmart.core.persistence.CompanyRepository;
import com.handchina.yunmart.core.persistence.UserRepository;
import com.handchina.yunmart.core.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Supplier;

/**
 * Created by markfredchen on 9/18/15.
 */
@Service
public class AccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Transactional
    public Company createCompany(Company company, String companyName) {
        String login = SecurityUtils.getCurrentLogin();
        Optional<User> userOptional = userRepository.findByEmail(login);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Account account = user.getAccount();
            if (!account.getName().equals(Constants.PUBLIC_ACCOUNT)) {
                throw new ValidationException(new ValidationError("user", "user.already.assign.to.company"));
            }
            company.setCompanyOID(UUID.randomUUID());
            companyRepository.save(company);
            Account companyAccount = new Account();
            companyAccount.setName(companyName);
            companyAccount.setAccountOID(UUID.randomUUID());
            companyAccount.setOwnerEntityOID(company.getCompanyOID());
            Set<Authority> newAuthorities = authorityRepository.findByNameIn(new HashSet<>(Arrays.asList(Constants.ROLE_COMPANY_ADMIN, Constants.ROLE_COMPANY_BILLING_ADMIN)));
            user.getRights().addAll(newAuthorities);
            accountRepository.save(companyAccount);
            user.setAccount(companyAccount);
            user.setCompanyName(companyName);
            userRepository.save(user);
            return company;
        } else {
            throw new ValidationException(new ValidationError("user", "user.not.found"));
        }

    }

}
