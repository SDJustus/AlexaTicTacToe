package de.tud.alexa.handler;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.model.Response;

public class HelpIntentHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Sag einfach zum Beispiel: 'ich moechte spielen' um zu spielen oder 'ich habe genug gespielt' um das Spiel zu beenden";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("TicTacToeHelp", speechText)
                .withReprompt(speechText)
                .build();
    }
}
