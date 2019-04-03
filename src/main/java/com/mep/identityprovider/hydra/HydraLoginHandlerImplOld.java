package com.mep.identityprovider.hydra;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HydraLoginHandlerImplOld {

    public String resourceUrl = "http://192.168.1.74:4444";
    public String loginUrl = "/oauth2/auth/requests/login/";


    public JsonNode getLoginRequest(String challengeCode) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = resourceUrl + loginUrl +challengeCode;
        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            //assertThat(name.asText(), notNullValue());
            return root;
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(90);
        }
        return null;
    }


    public String acceptLoginRequest(String challengeCode, String userId, boolean remember) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = resourceUrl + loginUrl + challengeCode + "/accept";
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("acr", "h");
        jsonMap.put("force_subject_identifier", "l");
        jsonMap.put("remember", remember);
        jsonMap.put("remember_for", 0);
        jsonMap.put("subject", userId);

        HttpEntity<?> requestPayload = new HttpEntity<>(jsonMap);
        String redirectTo = restTemplate.exchange(requestUrl, HttpMethod.PUT, requestPayload, String.class).getBody();
        // TODO add error handler for non 200 status

        return redirectTo;
    }


    public String denyLoginRequest(String challengeCode) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = resourceUrl + loginUrl + challengeCode + "/accept";
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("error", "login rejected");
        jsonMap.put("error_debug", "login rejected");
        jsonMap.put("error_description", "The login request was rejected by the identity server");
        jsonMap.put("error_hint", "Denied request. Generic error");
        jsonMap.put("status_code", 0);

        HttpEntity<?> requestPayload = new HttpEntity<>(jsonMap);
        String redirectTo = restTemplate.exchange(requestUrl, HttpMethod.PUT, requestPayload, String.class).getBody();
        // TODO add error handler for non 200 status

        return redirectTo;
    }
}
