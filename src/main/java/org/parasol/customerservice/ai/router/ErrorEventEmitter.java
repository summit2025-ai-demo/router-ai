package org.parasol.customerservice.ai.router;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

@ApplicationScoped
public class ErrorEventEmitter {

    @Inject
    @Channel("error")
    Emitter<String> emitter;

    public void emit(String payload) {
        emitter.send(toMessage(payload));
    }

    private Message<String> toMessage(String payload) {
        return Message.of(payload);
    }
}
