package com.ispaca.articlemanagement.rest;

import com.ispaca.articlemanagement.entity.UserInfo;
import com.ispaca.articlemanagement.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PutMapping("/users/updateEmailVerified")
    public void updateEmailVerified(@RequestBody UserInfo userInfo) {
        if (userInfo.getEmailVerified() == null || userInfo.getId() == null) {
            throw new RuntimeException("emailVerified or id cannot be null");
        }
        userInfoService.updateEmailVerified(userInfo.getId(), userInfo.getEmailVerified());
    }

}
