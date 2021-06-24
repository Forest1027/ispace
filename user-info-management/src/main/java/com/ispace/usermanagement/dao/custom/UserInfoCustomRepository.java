package com.ispace.usermanagement.dao.custom;


import com.ispace.usermanagement.entity.UserInfo;

public interface UserInfoCustomRepository {
    void updateEmailVerified(int userId, boolean status);

    UserInfo saveUserInfo(UserInfo userInfo);
}
