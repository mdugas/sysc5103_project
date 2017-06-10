package ca.carleton.sce.actions;

import ca.carleton.sce.SendCommand;
import jason.NoValueException;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;

import java.util.logging.Level;

@SuppressWarnings("unused")
public class Dash extends Action {

    Dash() {
        super("dash");
    }

    @Override
    boolean doExecute(SendCommand agent, Structure structure) {
        if (structure.getArity() != 1) {
            this.getLogger().log(Level.WARNING, String.format("Invalid arity for dash action: expected 1, was %s", structure.getArity()));
            return false;
        }

        try {
            double distance = ((NumberTerm) structure.getTerm(0)).solve();
            agent.dash(10 * distance);
        } catch (NoValueException e) {
            this.getLogger().log(Level.WARNING, String.format("Invalid distance for dash action: %s", structure.getTerm(0)), e);
            return false;
        }

        return true;
    }
}
