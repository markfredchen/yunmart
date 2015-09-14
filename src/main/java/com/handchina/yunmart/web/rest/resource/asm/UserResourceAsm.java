package com.handchina.yunmart.web.rest.resource.asm;

import com.handchina.yunmart.core.domain.Authority;
import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.web.rest.resource.UserResource;
import org.springframework.hateoas.ResourceAssembler;

import java.util.stream.Collectors;

/**
 * Created by markfredchen on 9/13/15.
 */
public class UserResourceAsm implements ResourceAssembler<User, UserResource>{

    @Override
    public UserResource toResource(User user) {
        UserResource userResource = new UserResource();
        userResource.setUserOID(user.getUserOID().toString());
        userResource.setUsername(user.getUsername());
        userResource.setFullName(user.getFullName());
        userResource.setTitle(user.getTitle());
        userResource.setAccountName(user.getAccount().getName());
        userResource.setAuthorities(user.getRights().stream().map(Authority::getName).collect(Collectors.toList()));
        return userResource;
    }

}
