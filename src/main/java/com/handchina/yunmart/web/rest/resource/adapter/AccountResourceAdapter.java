package com.handchina.yunmart.web.rest.resource.adapter;

import com.handchina.yunmart.core.domain.Account;
import com.handchina.yunmart.web.rest.resource.AccountResource;

import java.util.UUID;

/**
 * Created by markfredchen on 9/18/15.
 */
public class AccountResourceAdapter implements ResourceAdapter<Account, AccountResource> {
    @Override
    public Account toDomainObject(AccountResource accountResource) {
        Account account = new Account();
        account.setName(accountResource.getName());
        if (accountResource.getAccountOID() != null) {
            account.setAccountOID(UUID.fromString(accountResource.getAccountOID()));
        }
        if (accountResource.getOwnerEntityOID() != null) {
            account.setOwnerEntityOID(UUID.fromString(accountResource.getOwnerEntityOID()));
        }
        return account;
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource accountResource = new AccountResource();
        accountResource.setName(account.getName());
        accountResource.setAccountOID(account.getAccountOID().toString());
        if(account.getOwnerEntityOID() != null) {
            accountResource.setOwnerEntityOID(account.getOwnerEntityOID().toString());
        }
        return accountResource;
    }
}
