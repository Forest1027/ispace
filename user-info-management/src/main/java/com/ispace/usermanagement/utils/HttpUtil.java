package com.ispace.usermanagement.utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import com.ispace.usermanagement.entity.UserInfo;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class HttpUtil {
    @Value("${apikey}")
    private String apiKey;

    public static HttpResponse post(String uri, String data, String[] headers) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .headers(headers)
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return response;
    }

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

    public String[] getOktaRequestHeaders() {
        return new String[]{"Content-Type", "application/json", "Authorization", "SSWS "+apiKey, "Accept-Encoding", "gzip, deflate, br", "Accept", "*/*"};
    }
}
