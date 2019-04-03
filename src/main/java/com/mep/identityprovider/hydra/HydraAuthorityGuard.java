package com.mep.identityprovider.hydra;

import com.fasterxml.jackson.databind.JsonNode;

public interface HydraAuthorityGuard {

    boolean isLoginApproved(JsonNode json);
    boolean isConsentApproved(JsonNode json);

}
