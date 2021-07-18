package com.ispace.usermanagement.service;

import com.ispace.usermanagement.repository.UserInfoRepository;
import com.ispace.usermanagement.entity.UserInfo;
import com.ispace.utils.OktaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.http.HttpResponse;
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
    public Optional<UserInfo> findUserInfoById(String userId) {
        return userInfoRepository.findById(userId);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return userInfoRepository.saveUserInfo(userInfo);
    }

    @Override
    public UserInfo createUserInfo(UserInfo userInfo) throws Exception {
        HttpResponse response = OktaUtil.sendRegisterRequest(userInfo);
        if (response.statusCode() == 200) {
            return userInfoRepository.save(userInfo);
        }else {
            throw new RuntimeException(response.body().toString());
        }
    }

    @Override
    public List<UserInfo> getUsers() {
        return userInfoRepository.findAll();
    }

    @Override
    public void deleteUserInfoById(String userId) {
        userInfoRepository.deleteById(userId);
    }
}
