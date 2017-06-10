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
        // TODO: parameters...
        agent.move(-Math.random() * 52.5, 34 - Math.random() * 68.0);
        return true;
    }
}
