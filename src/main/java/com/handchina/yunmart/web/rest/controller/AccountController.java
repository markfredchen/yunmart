package com.handchina.yunmart.web.rest.controller;

import com.handchina.yunmart.core.domain.Account;
import com.handchina.yunmart.core.persistence.AccountRepository;
import com.handchina.yunmart.web.rest.resource.AccountResource;
import com.handchina.yunmart.web.rest.resource.adapter.AccountResourceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by markfredchen on 9/21/15.
 */
@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/api/accounts/{accountOID}", method = RequestMethod.GET)
    @ResponseBody
    public AccountResource getAccount(@PathVariable("accountOID") UUID accountOID) {
        Account account = accountRepository.findOneByAccountOID(accountOID);
        return new AccountResourceAdapter().toResource(account);
    }

}
