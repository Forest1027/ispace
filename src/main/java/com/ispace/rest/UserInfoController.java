package com.ispace.rest;

import com.ispace.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ispace.service.UserInfoService;

@RestController
@RequestMapping("/userManagement")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PutMapping("/v1/users/updateEmailVerified")
    public void updateEmailVerified(@RequestBody UserInfo userInfo) {
        if (userInfo.getEmailVerified() == null || userInfo.getId() == null) {
            throw new RuntimeException("emailVerified or id cannot be null");
        }
        userInfoService.updateEmailVerified(userInfo.getId(), userInfo.getEmailVerified());
    }

    @GetMapping("/v1/users/{userId}")
    public ResponseEntity getUserInfoById(@PathVariable String userId) {
        return new ResponseEntity(userInfoService.findUserInfoById(userId), HttpStatus.OK);
    }

    @PutMapping("/v1/users/{userId}")
    public ResponseEntity updateUserInfo(@PathVariable int userId, @RequestBody UserInfo userInfo) {
        return new ResponseEntity(userInfoService.updateUserInfo(userInfo), HttpStatus.OK);
    }

    @DeleteMapping("/v1/users/{userId}")
    public ResponseEntity deleteUserInfoById(@PathVariable String userId) {
        userInfoService.deleteUserInfoById(userId);
        return new ResponseEntity("Delete successfully", HttpStatus.OK);
    }

    @PostMapping("/v1/users/register")
    public ResponseEntity createUserInfo(@RequestBody UserInfo userInfo) throws Exception {
        userInfo.setId(null);
        userInfo.setEmailVerified(false);
        UserInfo createdUser = userInfoService.createUserInfo(userInfo);
        return new ResponseEntity(createdUser, HttpStatus.OK);
    }

    @GetMapping("/v1/users")
    public ResponseEntity getUsers() {
        return new ResponseEntity(userInfoService.getUsers(), HttpStatus.OK);
    }

}