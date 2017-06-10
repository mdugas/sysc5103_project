package ca.carleton.sce.actions;

import ca.carleton.sce.SendCommand;
import jason.NoValueException;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;

import java.util.logging.Level;

@SuppressWarnings("unused")
public class Move extends Action {

    Move() {
        super("move");
    }

    @Override
    boolean doExecute(SendCommand agent, Structure structure) {
        if (structure.getArity() != 2) {
            this.getLogger().log(Level.WARNING, String.format("Invalid arity for move action: expected 2, was %s", structure.getArity()));
            return false;
        }

        double x;
        double y;

        try {
            x = ((NumberTerm) structure.getTerm(0)).solve();
        } catch (NoValueException e) {
            this.getLogger().log(Level.WARNING, String.format("Invalid x for move action: %s", structure.getTerm(0)), e);
            return false;
        }

        try {
            y = ((NumberTerm) structure.getTerm(1)).solve();
        } catch (NoValueException e) {
            this.getLogger().log(Level.WARNING, String.format("Invalid y for move action: %s", structure.getTerm(0)), e);
            return false;
        }

        agent.move(x, y);

        return true;
    }
}
