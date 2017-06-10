package ca.carleton.sce.actions;

import ca.carleton.sce.SendCommand;
import jason.asSyntax.Structure;

@SuppressWarnings("unused")
public class Kick extends Action {

    Kick() {
        super("kick");
    }

    @Override
    boolean doExecute(SendCommand agent, Structure structure) {
        // TODO: parameters...
        agent.kick(100, 0);
        return true;
    }
}
