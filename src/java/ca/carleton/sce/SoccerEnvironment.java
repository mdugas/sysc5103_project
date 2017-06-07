package ca.carleton.sce;

import jason.asSyntax.Structure;
import jason.environment.Environment;

public class SoccerEnvironment extends Environment {
    
        
    @Override
    public void init(String[] args) {    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        String actionString = action.getFunctor();
        switch(actionString) {
            
            case "hello":
                System.out.println("Hello World");;
                return true;
            default:
                System.out.println("This action isn't implemented yet.");
        }
        
        return false;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
      super.stop();
    }
    
}
