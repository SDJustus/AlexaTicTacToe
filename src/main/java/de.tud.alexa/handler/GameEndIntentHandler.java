package de.tud.alexa.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class GameEndIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GameEndIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String speechText = "Du hast die Anwendung geschlossen!";
        return input.getResponseBuilder()
                .withSimpleCard("TicTacToeEndSession", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(true)
                .build();
    }

}
