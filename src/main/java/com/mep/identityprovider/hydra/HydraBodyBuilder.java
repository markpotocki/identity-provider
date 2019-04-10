package com.mep.identityprovider.hydra;

import java.util.Map;

public class HydraBodyBuilder {

    private static final Long REMEMBER_DURATION = 3600L;

    public static Map<String, ?> empty() {
        return Map.of();
    }

    public static Map<String, ?> acceptLoginBody(String subject, boolean remember) {
        return Map.of("subject", subject, "remember", remember, "remember_for", REMEMBER_DURATION);
    }
}
