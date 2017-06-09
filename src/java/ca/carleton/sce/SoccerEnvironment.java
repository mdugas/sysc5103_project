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
        boolean returnValue = false;
        
        String actionString = action.getFunctor();
        LOG.info("Action: " + actionString);
        switch(actionString) {
            
            case "turn":
                player.turn(40);
                returnValue = true;
                break;
            
            case "align":
            	player.turn(brain.getBallDirection());
            	returnValue = true;
            	break;
            	
            case "dash":
            	player.dash(10*brain.getBallDistance());
            	returnValue = true;
            	break;
                
            case "move":
                LOG.info("Are we before kickoff?");
                if(Pattern.matches("^before_kick_off.*",brain.getPlayMode())) {
                  LOG.info("We are before kickoff.");
                  player.move(-Math.random() * 52.5, 34 - Math.random() * 68.0);
                  LOG.info("I should have moved now.");
                  returnValue = true;
                }
                break;

            default:
                System.out.println("This action isn't implemented yet.");
        }
        
		try {
			Thread.sleep(2*SoccerParams.simulator_step);
		} catch(Exception e){
			// Do nothing if an exception is caught
		}
        
        updatePercepts();
        return returnValue;
    }
    
    /*
     * This method is used to send information back to the ASL environment.
     */
    void updatePercepts() {
    	// Define the belief percepts
    	Literal seeBall = Literal.parseLiteral("seeBall(true)");
        Literal inKickRange = Literal.parseLiteral("inKickRange(true)");
        Literal ballCentered = Literal.parseLiteral("ballCentered(true)");
    	Literal seeGoal = Literal.parseLiteral("seegoal(true)");
    	
    	// Remove the percepts, without removing those we don't want to remove
    	removePercept(seeBall);
    	removePercept(inKickRange);
    	removePercept(ballCentered);
    	removePercept(seeGoal);
    	
    	// Add the percepts based on the sensors.
        List<String> sensors = brain.getSensors();
        for(String sensor : sensors) {
            if(sensor == "seeball") {
            	LOG.info("Adding Percept seeBall(true)");
                addPercept(seeBall);
            }
            
            if(sensor == "inkickrange") {
            	LOG.info("Adding Percept inKickRange(true)");
                addPercept(inKickRange);
            }
            
            if(sensor == "ballcentered") {
            	LOG.info("Adding Percept ballCentered(true)");
                addPercept(ballCentered);
            }
            
            if(sensor == "seegoal") {
            	LOG.info("Adding Percept seeGoal(true)");
                addPercept(seeGoal);
            }
        }
        
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
      super.stop();
    }
    
}
