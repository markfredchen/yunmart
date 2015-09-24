package com.handchina.yunmart.web.rest.resource.adapter;

import com.handchina.yunmart.core.domain.Account;
import com.handchina.yunmart.core.domain.Authority;
import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.enumeration.UserStatus;
import com.handchina.yunmart.web.rest.resource.UserResource;

import java.util.UUID;
import java.util.stream.Collectors;
/**
 * Created by markfredchen on 9/13/15.
 */
public class UserResourceAdapter implements ResourceAdapter<User, UserResource>{

    @Override
    public User toDomainObject(UserResource userResource) {
        User user = new User();
        if (userResource.getUserOID() != null) {
            user.setUserOID(UUID.fromString(userResource.getUserOID()));
        }
        user.setEmail(userResource.getEmail());
        user.setPassword(userResource.getPassword());
        user.setFirstName(userResource.getFirstName());
        user.setLastName(userResource.getLastName());
        user.setMobile(userResource.getMobile());
        Account account = new Account();
        account.setAccountOID(UUID.fromString(userResource.getAccountOID()));
        user.setAccount(account);
        if(userResource.getUserStatus() != null) {
            user.setStatus(UserStatus.valueOf(userResource.getUserStatus()));
        }
        user.setAvatarFileType(userResource.getAvatarFileType());
        user.setCompanyName(userResource.getCompanyName());
        if (userResource.getRoles() != null) {
            user.setRights(userResource.getRoles().stream().map(right -> {
                Authority authority = new Authority();
                authority.setName(right);
                return authority;
            }).collect(Collectors.toSet()));
        }
        return user;
    }

    public UserResource toResource(User user) {
        UserResource userResource = new UserResource();
        userResource.setUserOID(user.getUserOID().toString());
        userResource.setEmail(user.getEmail());
        userResource.setFirstName(user.getFirstName());
        userResource.setLastName(user.getLastName());
        userResource.setMobile(user.getMobile());
        userResource.setCompanyName(user.getCompanyName());
        userResource.setUserStatus(user.getStatus().toString());
        userResource.setAvatarFileType(user.getAvatarFileType());
        userResource.setAccountOID(user.getAccount().getAccountOID().toString());
        if (user.getAccount().getOwnerEntityOID() != null) {
            userResource.setCompanyOID(user.getAccount().getOwnerEntityOID().toString());
        }
        userResource.setRoles(user.getRights().stream().map(Authority::getName).collect(Collectors.toSet()));
        return userResource;
    }
}
