package com.ispaca.articlemanagement.service;

import com.ispaca.articlemanagement.dao.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void updateEmailVerified(int userId, boolean status) {
        userInfoRepository.updateEmailVerified(userId, status);
    }
}
