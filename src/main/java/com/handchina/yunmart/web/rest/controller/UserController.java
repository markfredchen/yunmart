package com.handchina.yunmart.web.rest.controller;

import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.persistence.UserRepository;
import com.handchina.yunmart.core.security.SecurityUtils;
import com.handchina.yunmart.core.service.UserService;
import com.handchina.yunmart.web.rest.resource.UserResource;
import com.handchina.yunmart.web.rest.resource.adapter.UserResourceAdapter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by markfredchen on 9/13/15.
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @Value("${yunmart.user.avatar.path}")
    private String userAvatarPath;

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    @ResponseBody
    public UserResource createUser(@Valid @RequestBody UserResource userResource) {
        User user = userService.registerUser(new UserResourceAdapter().toDomainObject(userResource));
        // Send email to user
        return new UserResourceAdapter().toResource(user);
    }

    @RequestMapping(value = "/api/user/avatar/{userOID}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> updateUserAvatar(@PathVariable("userOID") UUID userOID, @RequestParam("image") String image) throws IOException {
        User user = repository.findByUserOID(userOID);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        } else if (!SecurityUtils.getCurrentLogin().equals(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            // save file
            String fileType = image.substring(11, image.indexOf(";"));
            FileOutputStream fos = new FileOutputStream(new File(userAvatarPath + userOID.toString() + "." + fileType));
            fos.write(Base64.decodeBase64(image.substring(image.indexOf(",") + 1)));
            fos.flush();
            fos.close();
            // update avatar
            if (user.getAvatarFileType() == null || !user.getAvatarFileType().equals(fileType)) {
                user.setAvatarFileType(fileType);
                repository.save(user);
            }
            return ResponseEntity.ok().build();
        }
    }


}
