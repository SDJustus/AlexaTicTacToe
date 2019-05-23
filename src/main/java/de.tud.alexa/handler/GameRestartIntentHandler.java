package de.tud.alexa.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class GameRestartIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GameRestartIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Du hast das Spiel neu gestartet";
        URL myUrl = null;
        try {
             myUrl = new URL("http://7795f34b.ngrok.io/restart");
            HttpURLConnection myURLConnection = (HttpURLConnection) myUrl.openConnection();
            myURLConnection.setRequestMethod("GET");
            myURLConnection.connect();
            myURLConnection.getInputStream();
            myURLConnection.disconnect();
        } catch (Exception e) {
            speechText.concat(" mit einem Fehler");
        }


        return input.getResponseBuilder()
                .withSimpleCard("TicTacToeRestartSession", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }

}
