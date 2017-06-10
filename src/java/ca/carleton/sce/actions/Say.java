package ca.carleton.sce.actions;

import ca.carleton.sce.SendCommand;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;

@SuppressWarnings("unused")
public class Say extends Action {

    Say() {
        super("say");
    }

    @Override
    boolean doExecute(SendCommand agent, Structure structure) {
        structure.getTerms().stream().map(Term::toString).forEach(agent::say);
        return true;
    }
}
