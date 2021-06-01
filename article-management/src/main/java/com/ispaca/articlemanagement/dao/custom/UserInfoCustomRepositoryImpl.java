package com.ispaca.articlemanagement.dao.custom;

import com.ispaca.articlemanagement.entity.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class UserInfoCustomRepositoryImpl implements UserInfoCustomRepository {
    @PersistenceContext
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
}
