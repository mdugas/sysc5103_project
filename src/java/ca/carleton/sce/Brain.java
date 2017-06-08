package ca.carleton.sce;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.*;

class Brain implements SensorInput {
    //===========================================================================
    // Private members
    private SendCommand         m_krislet;          // robot which is controlled by this brain
    private Memory              m_memory;           // place where all information is stored
    private char                m_side;
    volatile private boolean    m_timeOver;
    private String              m_playMode;
    
    private static final Logger LOG = Logger.getLogger(Brain.class.getName());

    //---------------------------------------------------------------------------
    // This constructor:
    // - stores connection to krislet
    // - starts thread for this object
    public Brain(SendCommand krislet, String team, char side, int number, String playMode) {
        m_timeOver = false;
        m_krislet = krislet;
        m_memory = new Memory();
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

    public List<String> getSensors() {
        ArrayList<String> sensors = new ArrayList<>();
        
        // Body, Ball and Goal objects
        ObjectInfo body_object = m_memory.getObject("body");
        ObjectInfo ball_object = m_memory.getObject("ball");
        ObjectInfo goal_object;
        
        //Get ball related sensor inputs
        if( ball_object != null ) {
            sensors.add("seeball");
            if(ball_object.m_distance <= 1.0) {
                sensors.add("inkickrange");
            }
            if(ball_object.m_direction == 0) {
                sensors.add("ballcentered");
            }
        }

        
        //Get goal related sensor inputs
        if( m_side == 'l' ) {
            goal_object = m_memory.getObject("goal r");
        }
        else {
            goal_object = m_memory.getObject("goal l");
        }
        if( goal_object != null ) {
            sensors.add("seegoal");
        }
       
        
        return sensors;
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
        m_memory.store(info);
    }


    //---------------------------------------------------------------------------
    // This function receives hear information from player
    public void hear(int time, int direction, String message) {
        //Do nothing if we call this method
    }

    //---------------------------------------------------------------------------
    // This function receives hear information from referee
    public void hear(int time, String message) {
        if(message.compareTo("time_over") == 0) {
            m_timeOver = true;
        }
    }
}
