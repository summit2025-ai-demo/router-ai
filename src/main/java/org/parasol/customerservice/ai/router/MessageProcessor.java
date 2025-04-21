package org.parasol.customerservice.ai.router;

import dev.langchain4j.model.chat.Capability;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@ApplicationScoped
public class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @ConfigProperty(name = "langchain4j.openai.modelname")
    String modelName;

    @ConfigProperty(name = "langchain4j.openai.apikey")
    String apiKey;

    @ConfigProperty(name = "langchain4j.openai.baseurl")
    String url;

    ChatLanguageModel model;

    RouteSelector routeSelector;

    void onStart(@Observes StartupEvent e) {
        model = initChatLanguageModel();
        routeSelector = initAiService();
    }

    public SelectedRoute processMessage(String contents) {
        return routeSelector.selectRoute(contents);
    }

    private ChatLanguageModel initChatLanguageModel() {
        LOGGER.info("Initializing model");
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .baseUrl(url)
                .logRequests(true)
                .logResponses(true)
                .supportedCapabilities(Set.of(Capability.RESPONSE_FORMAT_JSON_SCHEMA))
                .strictJsonSchema(true)
                .temperature(0.0)
                .build();
    }

    private RouteSelector initAiService() {
        LOGGER.info("Initializing AiService");
        return AiServices.create(RouteSelector.class, model);
    }

}
