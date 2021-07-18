package com.ispace.utils;

import org.json.JSONObject;
import java.util.Base64;

public class JwtUtil {
    public static String[] decodeJwtToken(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        return new String[]{header, payload};
    }

    public static JSONObject getCurrentUserPayload(String token) {
        String[] userInfo = decodeJwtToken(token);
        return new JSONObject(userInfo[1]);
    }

    public static String getCurrentUserEmailFromAuthorization(String token) {
        String[] strings = token.split(" ");
        String[] results = decodeJwtToken(strings[1]);
        return new JSONObject(results[1]).get("sub").toString();
    }

}
