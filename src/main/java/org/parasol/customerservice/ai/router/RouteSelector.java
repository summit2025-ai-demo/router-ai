package org.parasol.customerservice.ai.router;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface RouteSelector {

    @SystemMessage("""
            You are an AI-powered message classifier for an enterprise support system.
            Your task is to analyze email messages and determine the most appropriate team for handling them:
            - **Support**: Issues related to technical support, product usage, and troubleshooting.
            - **Finance**: Questions about billing, invoices, receipts, payments, refunds, or financial disputes.
            - **Website**: Issues related to website functionality, login problems, passport reset, account access, or technical errors on the website.
            - **Unknown**: If the message does not fit into any of the above categories or lacks sufficient context to classify accurately.
            """)
    @UserMessage("""
            Classify the following email message and determine the appropriate routing category (Support, Finance, Website, or Unknown).
            {{it}}
            """)
    SelectedRoute selectRoute(String text);
}
