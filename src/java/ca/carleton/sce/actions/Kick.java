package ca.carleton.sce.actions;

import java.util.logging.Level;

import ca.carleton.sce.SendCommand;
import jason.NoValueException;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;

@SuppressWarnings("unused")
public class Kick extends Action {

    Kick() {
        super("kick");
    }

    @Override
    boolean doExecute(SendCommand agent, Structure structure) {
    	double direction = 0;
        try {
            direction = ((NumberTerm) structure.getTerm(0)).solve();
        } catch (NoValueException e) {
            this.getLogger().log(Level.WARNING, String.format("Invalid moment for kick action: %s", structure.getTerm(0)), e);
            return false;
        }
    	
        agent.kick(100, direction);
        return true;
    }
}
