package org.parasol.customerservice.ai.router;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RouterEmitter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouterEmitter.class);

    @Inject
    @Channel("support")
    Emitter<String> supportEmitter;

    @Inject
    @Channel("finance")
    Emitter<String> financeEmitter;

    @Inject
    @Channel("unknown")
    Emitter<String> unknownEmitter;

    @Inject
    @Channel("website")
    Emitter<String> websiteEmitter;

    public void emit(String payload, Channels channel) {
        Channels channelInternal = channel;
        if (channel == null) {
            LOGGER.warn("Channel is null. Sending to 'unknown' emitter");
            channelInternal = Channels.UNKNOWN;
        }
        switch (channelInternal) {
            case SUPPORT -> supportEmitter.send(toMessage(payload));
            case FINANCE -> financeEmitter.send(toMessage(payload));
            case WEBSITE -> websiteEmitter.send(toMessage(payload));
            case UNKNOWN -> unknownEmitter.send(toMessage(payload));
        }
    }

    private Message<String> toMessage(String payload) {
        return Message.of(payload);
    }

}
