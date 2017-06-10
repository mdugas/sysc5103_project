package ca.carleton.sce.actions;

import ca.carleton.sce.SendCommand;
import jason.asSyntax.Structure;

@SuppressWarnings("unused")
public class Move extends Action {

    Move() {
        super("move");
    }

    @Override
    boolean doExecute(SendCommand agent, Structure structure) {

    	// Move to a random position on our side of the field.
        agent.move(-Math.random() * 52.5, 34 - Math.random() * 68.0);
        return true;
    }
}
