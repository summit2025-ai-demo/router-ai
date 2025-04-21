package org.parasol.customerservice.ai.router;

public enum Channels {

    SUPPORT, FINANCE, WEBSITE, UNKNOWN;

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
