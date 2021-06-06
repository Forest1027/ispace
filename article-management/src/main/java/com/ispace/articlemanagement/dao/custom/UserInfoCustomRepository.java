package com.ispace.articlemanagement.dao.custom;

import com.ispace.articlemanagement.entity.UserInfo;

public interface UserInfoCustomRepository {
    void updateEmailVerified(int userId, boolean status);

    UserInfo saveUserInfo(UserInfo userInfo);
}
