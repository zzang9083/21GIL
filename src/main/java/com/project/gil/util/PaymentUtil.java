package com.project.gil.util;

import com.project.gil.domain.Ott;
import com.project.gil.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentUtil {

    private final String RESTAPI_KEY = "6611561436752785";
    private final String RESTAPI_SECRET = "gOuuTAhp3l5GtRC1amhkriFelY7F1SWZy2KRgF05bWq76sspGFUOpc1ulyim04SX5BC5r2wO0kmRLGkl";
    private final String GET_TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private final String REQUEST_PAYMENT_URL = "https://api.iamport.kr/subscribe/payments/again";


    public String getToken() {

        RestTemplate restTemplate = new RestTemplate();

        //header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String,Object> map = new HashMap<>();
        map.put("imp_key", RESTAPI_KEY);
        map.put("imp_secret", RESTAPI_SECRET);

        //body
        Gson gson = new Gson();
        String json= gson.toJson(map);

        //request
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        return restTemplate.postForObject(GET_TOKEN_URL, entity, String.class);
    }

    public String requestPayment() {

        String token = getToken();

        Gson str = new Gson();
        token = token.substring(token.indexOf("response")+10);
        System.out.println("token1:"+token);
        token = token.substring(0, token.length()-1);
        System.out.println("token2:"+token);

        TokenVO vo = str.fromJson(token, TokenVO.class);
        String access_token = vo.getAccess_token();
        System.out.println("access_token:"+access_token);

        RestTemplate restTemplate = new RestTemplate();

        //header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(access_token);

        Map<String,Object> map = new HashMap<>();
        map.put("customer_uid", "0002");
        map.put("merchant_uid", "0002");
        map.put("amount", "10000");
        map.put("name", "test05");

        //body
        Gson gson = new Gson();
        String json= gson.toJson(map);
        System.out.println(json);
        //request
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        return restTemplate.postForObject(REQUEST_PAYMENT_URL, entity, String.class);
    }

    @Data
    @AllArgsConstructor
    static class TokenVO {
        private String access_token;
        private long now;
        private long expired_at;
    }

}
