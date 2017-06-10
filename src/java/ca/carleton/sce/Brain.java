package ca.carleton.sce;

import ca.carleton.sce.sensors.SensorInfo;
import ca.carleton.sce.sensors.SensorInput;
import ca.carleton.sce.sensors.hearing.Message;
import ca.carleton.sce.sensors.hearing.Sender;
import ca.carleton.sce.sensors.vision.VisualInfo;
import ca.carleton.sce.util.Mutex;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class Brain implements SensorInput {
    //===========================================================================
    // Private members
    private SendCommand         m_krislet;          // robot which is controlled by this brain
    private char                m_side;
    volatile private boolean    m_timeOver;
    private String              m_playMode;
    private Mutex<SensorInfo> sensorInfo = new Mutex<>(new SensorInfo());
    
    private static final Logger LOG = Logger.getLogger(Brain.class.getName());

    //---------------------------------------------------------------------------
    // This constructor:
    // - stores connection to krislet
    // - starts thread for this object
    public Brain(SendCommand krislet, String team, char side, int number, String playMode) {
        m_timeOver = false;
        m_krislet = krislet;
        //m_team = team;
        m_side = side;
        // m_number = number;
        m_playMode = playMode;
        
    }

    
    // This function returns a list of all percepts to be added to the agent.
    public List<Literal> getPercepts() {
        ArrayList<Literal> percepts = new ArrayList<>();

        
        this.sensorInfo.lock(sensorInfo -> {
        	
        	// Ball percepts
            sensorInfo.getBallList().stream().findAny().ifPresent(ball -> {
                percepts.add(ASSyntax.createLiteral("seeBall", ASSyntax.createNumber(ball.getDirection()), ASSyntax.createNumber(ball.getDistance())));
                if (ball.getDistance() <= 1d) {
                    percepts.add(Literal.parseLiteral("inKickRange(true)"));
                }
                if (ball.getDirection() == 0) {
                    percepts.add(Literal.parseLiteral("ballAligned(true)"));
                }
            });

            // Goal percepts
            sensorInfo.getGoalList().stream().filter(goal -> goal.getSide() != m_side).findAny().ifPresent(
            		goal -> 
            			percepts.add(ASSyntax.createLiteral("seeGoal", ASSyntax.createNumber(goal.getDirection()))));

            sensorInfo.clear();
        });

        for (Literal s : percepts) {
            LOG.log(Level.INFO, s.toString());
        }

        return percepts;
    }
    
    public boolean isTimeOver() {
        return m_timeOver;
    }

    //===========================================================================
    // Here are supporting functions for implement logic
    public String getPlayMode() {
        return this.m_playMode;
    }

    //===========================================================================
    // Implementation of SensorInput Interface

    //---------------------------------------------------------------------------
    // This function sends see information
    public void see(VisualInfo info) {
        this.sensorInfo.lock(s -> s.see(info));
    }


    //---------------------------------------------------------------------------
    // This function receives hear information from player
    public void hear(Message message) {
        if (message.getSender().equals(Sender.Referee) && "time_over".equals(message.getMessage())) {
            m_timeOver = true;
        } else {
            this.sensorInfo.lock(s -> s.hear(message));
        }
    }
}
