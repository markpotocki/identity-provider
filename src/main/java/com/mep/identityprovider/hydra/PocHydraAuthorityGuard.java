package com.mep.identityprovider.hydra;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class PocHydraAuthorityGuard implements HydraAuthorityGuard {

    @Override
    public boolean isLoginApproved(JsonNode json) {
        return true;
    }

    @Override
    public boolean isConsentApproved(JsonNode json) {
        return false;
    }
}
