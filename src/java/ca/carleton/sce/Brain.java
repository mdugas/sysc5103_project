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


    //---------------------------------------------------------------------------
    // This is main brain function used to make decision
    // In each cycle we decide which command to issue based on
    // current situation. the rules are:
    //
    //	1. If you don't know where is ball then turn right and wait for new info
    //
    //	2. If ball is too far to kick it then
    //		2.1. If we are directed towards the ball then go to the ball
    //		2.2. else turn to the ball
    //
    //	3. If we don't know where is opponent goal then turn wait
    //				and wait for new info
    //
    //	4. Kick ball
    //
    //	To ensure that we don't send commands to often after each cycle
    //	we waits one simulator steps. (This of course should be done better)

    // ***************  Improvements ******************
    // Always know where the goal is.
    // Move to a place on my side on a kick_off
    // ************************************************

//    public void run() {
//        ObjectInfo object;
//
//        // first put it somewhere on my side
//        if(Pattern.matches("^before_kick_off.*",m_playMode)) {
//            m_krislet.move(-Math.random() * 52.5, 34 - Math.random() * 68.0);
//        }
//
//        while( !m_timeOver ) {
//            object = m_memory.getObject("ball");
//            if( object == null ) {
//                // If you don't know where is ball then find it
//                m_krislet.turn(40);
//                m_memory.waitForNewInfo();
//            }
//            else if( object.m_distance > 1.0 ) {
//                // If ball is too far then
//                // turn to ball or
//                // if we have correct direction then go to ball
//                if( object.m_direction != 0 ) {
//                    m_krislet.turn(object.m_direction);
//                }
//                else {
//                    m_krislet.dash(10 * object.m_distance);
//                }
//            }
//            else {
//                // We know where is ball and we can kick it
//                // so look for goal
//                if( m_side == 'l' ) {
//                    object = m_memory.getObject("goal r");
//                }
//                else {
//                    object = m_memory.getObject("goal l");
//                }
//
//                if( object == null ) {
//                    m_krislet.turn(40);
//                    m_memory.waitForNewInfo();
//                }
//                else {
//                    m_krislet.kick(100, object.m_direction);
//                }
//            }
//
//            // sleep one step to ensure that we will not send
//            // two commands in one cycle.
//            try {
//                Thread.sleep(2*SoccerParams.simulator_step);
//            } catch(Exception e){
//                // Do nothing if an exception is caught
//            }
//        }
//        m_krislet.bye();
//    }

    public List<Literal> getPercepts() {
        ArrayList<Literal> percepts = new ArrayList<>();

        this.sensorInfo.lock(sensorInfo -> {
            sensorInfo.getBallList().stream().findAny().ifPresent(ball -> {
                percepts.add(ASSyntax.createLiteral("seeBall", ASSyntax.createNumber(ball.getDirection()), ASSyntax.createNumber(ball.getDistance())));
            });

            sensorInfo.getGoalList().stream().filter(goal -> goal.getSide() != m_side).findAny()
                    .ifPresent(goal -> percepts.add(ASSyntax.createLiteral("seeGoal", ASSyntax.createNumber(goal.getDirection()), ASSyntax.createNumber(goal.getDistance()))));

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
