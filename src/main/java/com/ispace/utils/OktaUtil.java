package com.ispace.utils;

import com.ispace.constants.OktaApiConstants;
import com.ispace.constants.OktaConfigConstants;
import com.ispace.usermanagement.entity.UserInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.http.HttpResponse;

public class OktaUtil {

    public static JSONObject createOktaUserRegisterJson(UserInfo userInfo) {
        JSONObject data = new JSONObject();
        JSONObject profile = new JSONObject();
        JSONObject credentials = new JSONObject();
        JSONObject password = new JSONObject();
        profile.put("firstName", userInfo.getFirstName());
        profile.put("lastName", userInfo.getLastName());
        profile.put("email", userInfo.getEmail());
        profile.put("login", userInfo.getEmail());
        password.put("value", userInfo.getPassword());
        credentials.put("password", password);
        data.put("profile", profile);
        data.put("credentials", credentials);
        return data;
    }

    public static String[] getOktaRequestHeaders() {
        return new String[]{"Content-Type", "application/json", "Authorization", "SSWS "+ OktaConfigConstants.apiKey, "Accept-Encoding", "gzip, deflate, br", "Accept", "*/*"};
    }

    public static HttpResponse sendRegisterRequest(UserInfo userInfo) throws Exception {
        JSONObject data = createOktaUserRegisterJson(userInfo);
        return HttpUtil.post(OktaConfigConstants.baseUrl + OktaConfigConstants.apiPaths.get(OktaApiConstants.CREATE_USER_WITH_PASSWORD), data.toString(), getOktaRequestHeaders());
    }
}
