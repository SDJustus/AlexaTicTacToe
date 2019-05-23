package de.tud.alexa.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;

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

        Slot rowSlot = slots.get("Row");
        Slot colSlot = slots.get("Column");
        String speechText, repromptText;

        if(rowSlot != null) {

            String rowSlotValue = rowSlot.getValue();

            speechText =
                    String.format("Du hast die Reihe " + rowSlotValue + " gewählt.");
            repromptText =
                    "Moechtest du nochmal wuerfeln?";

        } else {

            speechText = "Ich habe die Reihe, in der du dein Zug machen willst, leider nicht verstanden.";
            repromptText =
                    "Bist du noch da? Sag einfach Hilfe, wenn du nicht weiter weist.";
        }
        if (colSlot != null){
            String colSlotValue = colSlot.getValue();

            speechText =
                    speechText.concat(" und du hast die Spalte " + colSlotValue + "gewählt!");
            repromptText =
                    "Moechtest du nochmal wuerfeln?";

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

