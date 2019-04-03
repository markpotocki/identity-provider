package com.mep.identityprovider.hydra;


import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface HydraLoginHandler {

    JsonNode getLoginRequest(String challengeCode);
    JsonNode getConsentRequest(String challengeCode);

    String acceptLoginRequest(String challengeCode, Map<String, ?> body);
    String denyLoginRequest(String challengeCode, Map<String, ?> body);

    String acceptConsentRequest(String challengeCode, Map<String, ?> body);
    String denyConsentRequest(String challengeCode, Map<String, ?> body);



}
