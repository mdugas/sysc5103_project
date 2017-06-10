package ca.carleton.sce.actions;

import ca.carleton.sce.SendCommand;
import jason.NoValueException;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;

import java.util.logging.Level;

@SuppressWarnings("unused")
public class Turn extends Action {

    Turn() {
        super("turn");
    }

    @Override
    boolean doExecute(SendCommand agent, Structure structure) {
        if (structure.getArity() != 1) {
            this.getLogger().log(Level.WARNING, String.format("Invalid arity for turn action: expected 1, was %s", structure.getArity()));
            return false;
        }

        try {
            double moment = ((NumberTerm) structure.getTerm(0)).solve();
            agent.turn(moment);
        } catch (NoValueException e) {
            this.getLogger().log(Level.WARNING, String.format("Invalid moment for turn action: %s", structure.getTerm(0)), e);
            return false;
        }

        return true;
    }
}
