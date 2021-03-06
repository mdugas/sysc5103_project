package ca.carleton.sce;

import ca.carleton.sce.actions.Action;
import jason.asSyntax.Structure;
import jason.environment.Environment;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoccerEnvironment extends Environment {


    private static final Logger LOGGER = Logger.getLogger(SoccerEnvironment.class.getName());
    private Krislet player;
    private Brain brain;
    private Collection<Action> actions = Action.getAll();

    @Override
    public void init(String[] args) {
        super.init(args);

        String hostName = "localhost";
        int port = 6000;
        String team = "Raven";

        try {
        	LOGGER.log(Level.FINE,"Initializing phase");
        	LOGGER.log(Level.FINE,hostName);
            player = new Krislet(InetAddress.getByName(hostName), port, team);
            brain = player.getBrain();
            LOGGER.log(Level.FINE,"Initialized");
        } catch (SocketException | UnknownHostException e) {
            LOGGER.log(Level.SEVERE, "Can't initialize agent!", e);
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        this.actions.stream().filter(a -> a.matches(action))
                .findAny()
                .map(a -> a.execute(this.player, action))
                .orElseGet(() -> {
                    LOGGER.warning(String.format("Invalid action: %s", action));
                    return false;
                });

        try {
            Thread.sleep(2 * SoccerParams.simulator_step);
        } catch (Exception e) {
            // Do nothing if an exception is caught
        }

        updatePercepts();

        // always return true so the agent doesn't stop in case of error
        return true;
    }

    /*
     * This method is used to send information back to the ASL environment.
     */
    private void updatePercepts() {
        this.clearPercepts("robo");

        this.brain.getPercepts().forEach(percept -> {
            this.addPercept("robo", percept);
            LOGGER.info(String.format("Adding percept: %s", percept.toString()));
        });
    }

    /**
     * Called before the end of MAS execution
     */
    @Override
    public void stop() {
        super.stop();
    }

}
