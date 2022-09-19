package com.project.gil.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/*
 * class name    : KakaoUtil
 * description   : 카카오 API연동 유틸 클래스
 *                 1. 카카오톡 연동 로그인
 *                   - getKaKaoAccessToken : 토큰값 받기
 *                   - getUserInfo : 사용자 정보받기
 *                 2. 카카오토 메시지 보내기
 * last modified : 2022.09.05 - 토큰값 받기
 *
 * */

@Service
public class KakaoUtil {
    private final String OAUTH_URL = "https://kauth.kakao.com/oauth/token"; // 토큰값 받기 URL
    private final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 사용자 정보 받기 URL
    private final String REDIRECT_URI = "http://192.168.45.30:8080/api/user/login"; // 리다이렉트 URL
    private final String CLIENT_ID = "f84d870623bee520d21503232e1444af"; // REST API


    /*
     * method name    : getKaKaoAccessToken
     * description   : 카카오 사용자 접근 토큰값 가지고오기
     *                 1. 카카오톡 연동 로그인
     *                   - getKaKaoAccessToken : 토큰값 받기
     *                   - getUserInfo : 사용자 정보받기
     *                 2. 카카오토 메시지 보내기
     * last modified : 2022.09.05 - 토큰값 받기
     *
     * */

    public String getKaKaoAccessToken(String code) {

        String accessToken = "";

        RestTemplate restTemplate = new RestTemplate();

        //header set
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //body set
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", CLIENT_ID);
        map.add("redirect_uri", REDIRECT_URI);
        map.add("code", code);

        //json parse
        Gson gson = new Gson();
        String json = gson.toJson(map);

        //request
        ResponseEntity<String> result = restTemplate.postForEntity(OAUTH_URL, map, String.class);

        //response
        if ((result.getStatusCode() == HttpStatus.OK)) {
            String body = result.getBody();

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(body);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();

        }

        return accessToken;
    }


    /*
     * method name    : getUserInfo
     * description    : 카카오 사용자정보 가지고오기
     * in             : (String) token - 사용자 액세스 토큰 값
     * out            : (String) email - 사용자 계정
     * */

    public Map getUserInfoByToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        Long id;
        String email = "";
        Map<String, Object> result= new HashMap<>();

        //header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.setBearerAuth(token);

        //request
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = restTemplate.postForEntity(USER_INFO_URL, entity, String.class);

        //response
        if ((response.getStatusCode() == HttpStatus.OK)) {
            String body = response.getBody();

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(body);

            id = element.getAsJsonObject().get("id").getAsLong();
            result.put("id",(Long)id);

            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            if (hasEmail)
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
                result.put("email",(String)email);
        }

        return result;

    }
}
