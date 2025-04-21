package org.parasol.customerservice.ai.router;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonObject;

public class SelectedRoute {

    @JsonProperty(required = true)
    Channels route;

    String reason;

    Boolean escalationRequired;

    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("route", route == null ? null : route.toString());
        jsonObject.put("reason", reason == null || reason.isEmpty() ? null : reason );
        jsonObject.put("escalation_required", escalationRequired == null ? null : escalationRequired);
        return jsonObject;
    }
}
