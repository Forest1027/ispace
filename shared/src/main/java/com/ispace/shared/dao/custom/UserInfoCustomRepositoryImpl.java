package com.ispace.shared.dao.custom;

import com.ispace.shared.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class UserInfoCustomRepositoryImpl implements UserInfoCustomRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void updateEmailVerified(int userId, boolean status) {
        UserInfo user = entityManager.find(UserInfo.class, userId);
        if (user != null) {
            user.setEmailVerified(status);
        } else {
            throw new RuntimeException("User does not exist. User Id: " + userId);
        }
        entityManager.merge(user);
    }

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        UserInfo user = entityManager.find(UserInfo.class, userInfo.getId());
        if (user == null) {
            throw new RuntimeException("User does not exist. User Id: " + userInfo.getId());
        } else {
            userInfo.setEmailVerified(user.getEmailVerified());
        }
        entityManager.merge(userInfo);
        return userInfo;
    }
}
