package com.ispaca.articlemanagement.service;

import com.ispaca.articlemanagement.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {
    void updateEmailVerified(int userId, boolean status);

    Optional<UserInfo> findUserInfoById(int userId);

    UserInfo updateUserInfo(UserInfo userInfo);

    UserInfo createUserInfo(UserInfo userInfo);

    List<UserInfo> getUsers();

    void deleteUserInfoById(int userId);
}
