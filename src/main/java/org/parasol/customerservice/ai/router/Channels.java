package org.parasol.customerservice.ai.router;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Channels {

    @JsonProperty("support")
    SUPPORT,
    @JsonProperty("finance")
    FINANCE,
    @JsonProperty("website")
    WEBSITE,
    @JsonProperty("unknown")
    UNKNOWN;

    @Override
    public String toString() {
        return switch (this) {
            case SUPPORT -> "support";
            case FINANCE -> "finance";
            case WEBSITE -> "website";
            case UNKNOWN -> "unknown";
            default -> null;
        };
    }

}
