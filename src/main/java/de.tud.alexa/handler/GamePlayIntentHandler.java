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

public class GamePlayIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GamePlayIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot rowSlot = slots.get("Row");    //Buchstabe {A,B,C} bzw. {Alpha, Beta, Gamma}
        Slot colSlot = slots.get("Column"); //Zahl      {1,2,3}
        String speechText = null;
        String repromptText;
        String colSlotValue;
        String rowSlotValue;

        if(rowSlot != null && colSlot != null) {

            rowSlotValue = rowSlot.getValue();

            if (rowSlotValue.length() != 1) {
                throw new IllegalArgumentException("Wrong Slot Value for Row Slot... Value was " + rowSlotValue);
            }

            colSlotValue = colSlot.getValue();
            if (colSlotValue.length() != 1) {
                throw new IllegalArgumentException("Wrong Slot Value for Column Slot... Value was " + colSlotValue);
            }

        String rowAndColumnSlotValue = rowSlotValue.concat(colSlotValue);

            URL myUrl = null;
            try {
                myUrl = new URL("http://7795f34b.ngrok.io/game/slot?value=" + rowAndColumnSlotValue);
                HttpURLConnection myURLConnection = (HttpURLConnection) myUrl.openConnection();
                myURLConnection.setRequestMethod("GET");
                myURLConnection.connect();
                myURLConnection.getInputStream();
                myURLConnection.disconnect();
            } catch (Exception e) {
                speechText.concat(" mit einem Fehler");
            }




            speechText = String.format("Du hast die Reihe %s und die Spalte %s gewählt. Der nächste Spieler ist dran!", rowSlotValue, colSlotValue);
            repromptText = "Bist du noch da?";

        } else {

            speechText = "Ich habe die Reihe, in der du dein Zug machen willst, leider nicht verstanden.";
            repromptText =
                    "Bist du noch da? Sag einfach Hilfe, wenn du nicht weiter weist.";
        }

        return input.getResponseBuilder()
                .withSimpleCard("TicTacToePlaySession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }

}

