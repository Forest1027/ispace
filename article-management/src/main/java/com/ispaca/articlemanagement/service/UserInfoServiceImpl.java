package com.ispaca.articlemanagement.service;

import com.ispaca.articlemanagement.dao.UserInfoRepository;
import com.ispaca.articlemanagement.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void updateEmailVerified(int userId, boolean status) {
        userInfoRepository.updateEmailVerified(userId, status);
    }

    @Override
    public Optional<UserInfo> findUserInfoById(int userId) {
        return userInfoRepository.findById(userId);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return userInfoRepository.saveUserInfo(userInfo);
    }

    @Override
    public UserInfo createUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> getUsers() {
        return userInfoRepository.findAll();
    }

    @Override
    public void deleteUserInfoById(int userId) {
        userInfoRepository.deleteById(userId);
    }
}
