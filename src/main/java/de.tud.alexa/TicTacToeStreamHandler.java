package de.tud.alexa;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import de.tud.alexa.handler.GameEndIntentHandler;
import de.tud.alexa.handler.GamePlayIntentHandler;
import de.tud.alexa.handler.GameRestartIntentHandler;
import de.tud.alexa.handler.LaunchRequestHandler;
import de.tud.alexa.handler.CancelandStopIntentHandler;
import de.tud.alexa.handler.HelpIntentHandler;
import de.tud.alexa.handler.SessionEndedRequestHandler;
import de.tud.alexa.handler.FallbackIntentHandler;



public class TicTacToeStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new GamePlayIntentHandler(),
                        new GameEndIntentHandler(),
                        new GameRestartIntentHandler(),
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler())
                .withSkillId(System.getenv("SKILLID"))
                .build();
    }

    public TicTacToeStreamHandler() {
        super(getSkill());
    }
// mvn clean org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
}