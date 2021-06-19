package com.ispace.usermanagement.service;


import com.ispace.usermanagement.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {
    void updateEmailVerified(int userId, boolean status);

    Optional<UserInfo> findUserInfoById(int userId);

    UserInfo updateUserInfo(UserInfo userInfo);

    UserInfo createUserInfo(UserInfo userInfo) throws Exception;

    List<UserInfo> getUsers();

    void deleteUserInfoById(int userId);
}
