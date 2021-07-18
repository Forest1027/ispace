package com.ispace.shared.repository.custom;


import com.ispace.shared.entity.UserInfo;

public interface UserInfoCustomRepository {
    void updateEmailVerified(int userId, boolean status);

    UserInfo saveUserInfo(UserInfo userInfo);
}
