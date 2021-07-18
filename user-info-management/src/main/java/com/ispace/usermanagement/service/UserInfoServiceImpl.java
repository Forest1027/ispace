package com.ispace.usermanagement.service;

import com.ispace.shared.repository.UserInfoRepository;
import com.ispace.shared.entity.UserInfo;
import com.ispace.usermanagement.constants.OktaUrlConstants;
import com.ispace.usermanagement.utils.HttpUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private HttpUtil httpUtil;

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
    public UserInfo createUserInfo(UserInfo userInfo) throws Exception {
        JSONObject data = HttpUtil.createOktaUserRegisterJson(userInfo);
        HttpResponse response = HttpUtil.post(OktaUrlConstants.BASE_URL + OktaUrlConstants.CREATE_USER_WITH_PASSWORD, data.toString(), httpUtil.getOktaRequestHeaders());
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
    public void deleteUserInfoById(int userId) {
        userInfoRepository.deleteById(userId);
    }
}
