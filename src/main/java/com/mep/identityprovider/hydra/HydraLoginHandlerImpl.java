package com.mep.identityprovider.hydra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Service
public class HydraLoginHandlerImpl implements HydraLoginHandler {

    @Override
    public JsonNode getLoginRequest(String challengeCode) {
        return get(challengeCode, "login");
    }

    @Override
    public String acceptLoginRequest(String challengeCode, Map<String, ?> body) {
        JsonNode json = put(challengeCode, "login", "accept", body);
        return json.findValue("redirect_to").asText();
    }

    @Override
    public String denyLoginRequest(String challengeCode, Map<String, ?> body) {
        JsonNode json = put(challengeCode, "login", "reject", body);
        return json.findValue("redirect_to").asText();
    }

    @Override
    public JsonNode getConsentRequest(String challengeCode) {
        return get(challengeCode, "consent");
    }

    @Override
    public String acceptConsentRequest(String challengeCode, Map<String, ?> body) {
        JsonNode json = put(challengeCode, "consent", "accept", body);
        return json.findValue("redirect_to").asText();
    }

    @Override
    public String denyConsentRequest(String challengeCode, Map<String, ?> body) {
        JsonNode json = put(challengeCode, "consent", "reject", body);
        return json.findValue("redirect_to").asText();
    }

    private final String HYDRA_URI = "http://192.168.1.74:4445/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private JsonNode get(String challenge, String flow) {
        RestTemplate restTemplate = new RestTemplate();
        String url = HYDRA_URI + "oauth2/auth/requests/" + flow + "/" + challenge;
        HttpEntity<String> response = restTemplate.getForEntity(url, String.class);
        try {
            return objectMapper.readTree(response.getBody());
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private JsonNode put(String challenge, String flow, String action, Map<String, ?> body) {
        RestTemplate restTemplate = new RestTemplate();
        String url = HYDRA_URI + "oauth2/auth/requests/" + flow + "/" + challenge + "/" + action;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String bd = objectMapper.writeValueAsString(body);
            RequestEntity<?> request = RequestEntity.put(URI.create(url)).accept(MediaType.APPLICATION_JSON).body(bd);
            ResponseEntity<String> response = restTemplate.exchange(request, String.class);

            return objectMapper.readTree(response.getBody());
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
