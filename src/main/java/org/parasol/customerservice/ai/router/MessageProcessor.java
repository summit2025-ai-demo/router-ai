package org.parasol.customerservice.ai.router;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @Inject
    RouteSelector routeSelector;

    public SelectedRoute processMessage(String contents) {
        return routeSelector.selectRoute(contents);
    }

}
