package com.ispace.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("constants.okta")
public class OktaConfigConstants {
    public static String baseUrl;
    public static Map<String, String> apiPaths;
    public static String apiKey;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        OktaConfigConstants.baseUrl = baseUrl;
    }

    public Map<String, String> getApiPaths() {
        return apiPaths;
    }

    public void setApiPaths(Map<String, String> apiPaths) {
        OktaConfigConstants.apiPaths = apiPaths;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        OktaConfigConstants.apiKey = apiKey;
    }
}
