package com.handchina.yunmart.web.rest.controller;

import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.service.UserService;
import com.handchina.yunmart.web.rest.resource.UserResource;
import com.handchina.yunmart.web.rest.resource.asm.UserResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by markfredchen on 9/13/15.
 */
@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public UserResource createUser(@Valid @RequestBody UserResource userResource) {
        User user = userService.createUser(userResource.toUser(), userResource.getAccountName(), userResource.getAuthorities());
        return new UserResourceAsm().toResource(user);
    }

}
