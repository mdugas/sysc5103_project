package ca.carleton.sce.actions;

import ca.carleton.sce.SendCommand;
import jason.NoValueException;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;

import java.util.logging.Level;

@SuppressWarnings("unused")
public class Kick extends Action {

    Kick() {
        super("kick");
    }

    @Override
    boolean doExecute(SendCommand agent, Structure structure) {
        if (structure.getArity() != 2) {
            this.getLogger().log(Level.WARNING, String.format("Invalid arity for dash action: expected 2, was %s", structure.getArity()));
            return false;
        }

        double power;
        double direction;

        try {
            power = ((NumberTerm) structure.getTerm(0)).solve();
        } catch (NoValueException e) {
            this.getLogger().log(Level.WARNING, String.format("Invalid power for dash action: %s", structure.getTerm(0)), e);
            return false;
        }

        try {
            direction = ((NumberTerm) structure.getTerm(1)).solve();
        } catch (NoValueException e) {
            this.getLogger().log(Level.WARNING, String.format("Invalid power for dash action: %s", structure.getTerm(0)), e);
            return false;
        }

        agent.kick(power, direction);

        return true;
    }
}
