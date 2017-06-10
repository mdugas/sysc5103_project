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

        // Get the parameter value given by the ASL call and dash using that value as distance.
        try {
            double power = ((NumberTerm) structure.getTerm(0)).solve();
            agent.dash(power);
        } catch (NoValueException e) {
            this.getLogger().log(Level.WARNING, String.format("Invalid power for dash action: %s", structure.getTerm(0)), e);
            return false;
        }

        return true;
    }
}
