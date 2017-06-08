package ca.carleton.sce;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class SoccerEnvironment extends Environment {
    
    
    private static final Logger LOG = Logger.getLogger(SoccerEnvironment.class.getName());
    private Krislet player;
    private Brain brain;
    
    @Override
    public void init(String[] args) {  
        try {
            LOG.info("Initializing phase");
            player = new Krislet(InetAddress.getByName(""), 6000, "Raven");
            brain = player.getBrain();
            LOG.info("Initialized");
        } catch (SocketException | UnknownHostException e) {
            // TODO Auto-generated catch block
            LOG.log(Level.SEVERE, "Can't initialize agent!",e);
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        String actionString = action.getFunctor();
        LOG.info("Action: " + actionString);
        switch(actionString) {
            
            case "turn":
                player.turn(40);
                return true;
                
            case "move":
                LOG.info("Are we before kickoff?");
                if(Pattern.matches("^before_kick_off.*",brain.getPlayMode())) {
                  LOG.info("We are before kickoff.");
                  player.move(-Math.random() * 52.5, 34 - Math.random() * 68.0);
                  LOG.info("I should have moved now.");
                  return true;
                }
                
            default:
                System.out.println("This action isn't implemented yet.");
        }
        
        return false;
    }
    
    /*
     * This method is used to send information back to the ASL environment.
     */
    void updatePercepts() {
        clearPercepts();
        
        List<String> sensors = brain.getSensors();
        for(String sensor : sensors) {
            if(sensor == "seeball") {
                addPercept(Literal.parseLiteral("seeball(true)"));
            }
            
            if(sensor == "inkickrange") {
                addPercept(Literal.parseLiteral("inkickrange(true)"));
            }
            
            if(sensor == "ballcentered") {
                addPercept(Literal.parseLiteral("ballcentered(true)"));
            }
            
            if(sensor == "seegoal") {
                addPercept(Literal.parseLiteral("seegoal(true)"));
            }
        }
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
      super.stop();
    }
    
}
