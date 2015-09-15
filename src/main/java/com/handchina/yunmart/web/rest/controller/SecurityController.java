package com.handchina.yunmart.web.rest.controller;

import com.handchina.yunmart.core.persistence.UserRepository;
import com.handchina.yunmart.core.service.UserService;
import com.handchina.yunmart.web.rest.resource.UserResource;
import com.handchina.yunmart.web.rest.resource.asm.UserResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by markfredchen on 9/13/15.
 */
@Controller
@RequestMapping("/api")
public class SecurityController extends BaseController{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/principal",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserResource> getPrincipal() {
        return Optional.ofNullable(userService.getCurrentUser())
            .map(user -> new ResponseEntity<>(new UserResourceAsm().toResource(user), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/forget/password/token")
    @ResponseBody
    public ResponseEntity<HashMap> getForgetPasswordToken(@RequestParam("usernameOrEmail") String usernameOrEmail) {
        System.out.println(usernameOrEmail);
        HashMap<String, UUID> result = new HashMap<>();
        result.put("token", userService.getForgetPasswordToken(usernameOrEmail).getForgetPasswordToken());
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/forget/password/reset")
    @ResponseBody
    public ResponseEntity<Void> resetPassword(@RequestParam("forgetPasswordToken") UUID forgetPasswordToken, @RequestParam("newPassword") String newPassword) {
        userService.resetPassword(forgetPasswordToken, newPassword);
        return ResponseEntity.ok().build();
    }
}
