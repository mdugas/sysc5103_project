package ca.carleton.sce;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import jason.NoValueException;
import jason.asSyntax.Literal;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class SoccerEnvironment extends Environment {
    
    
    private static final Logger LOG = Logger.getLogger(SoccerEnvironment.class.getName());
    private Krislet player;
    private Brain brain;
    
    @Override
    public void init(String[] args) {
        super.init(args);

        String	hostName = "192.168.0.22";
        int	    port = 6000;
        String	team = "Raven";

        try {
            LOG.info("Initializing phase");
            LOG.info(hostName);
            player = new Krislet(InetAddress.getByName(hostName), port, team);
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
                double moment = 40;
                try {
                    moment = ((NumberTerm) action.getTerm(0)).solve();
                } catch (NoValueException e) {
                }
                player.turn(moment);
            	returnValue = true;
            	break;
            	
            case "dash":
                double distance = 1;
                try {
                    distance = ((NumberTerm) action.getTerm(0)).solve();
                } catch (NoValueException e) {
                }
                player.dash(10 * distance);
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
        // Remove the percepts, without removing those we don't want to remove
        this.clearPercepts("robo");

        this.brain.getPercepts().forEach(percept -> {
            this.addPercept("robo", percept);
            LOG.info(String.format("Adding Percept %s", percept.toString()));
        });
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
      super.stop();
    }
    
}
