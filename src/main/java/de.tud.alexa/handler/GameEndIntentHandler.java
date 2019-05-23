package de.tud.alexa.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;

import java.net.HttpURLConnection;
import java.net.URL;
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
        URL myUrl = null;
        try {
            myUrl = new URL("http://7795f34b.ngrok.io/end");
            HttpURLConnection myURLConnection = (HttpURLConnection) myUrl.openConnection();
            myURLConnection.setRequestMethod("GET");
            myURLConnection.connect();
            myURLConnection.getInputStream();
            myURLConnection.disconnect();
        } catch (Exception e) {
            speechText.concat(" mit einem Fehler");
        }
        return input.getResponseBuilder()
                .withSimpleCard("TicTacToeEndSession", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(true)
                .build();
    }

}
