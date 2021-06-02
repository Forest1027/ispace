package com.ispaca.articlemanagement.dao.custom;

import com.ispaca.articlemanagement.entity.UserInfo;

public interface UserInfoCustomRepository {
    void updateEmailVerified(int userId, boolean status);

    UserInfo saveUserInfo(UserInfo userInfo);
}
