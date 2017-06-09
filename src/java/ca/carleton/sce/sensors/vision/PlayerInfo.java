package ca.carleton.sce.sensors.vision;

//	File:			robo.ObjectInfo.java
//	Author:		Krzysztof Langner
//	Date:			1997/04/28

//  Modified by:  Paul Marlow, Amir Ghavam, Yoga Selvaraj
//  Course:       Software Agents
//  Date Due:     November 30, 2000

//  Modified by:  Edgar Acosta
//  Date:         March 4, 2008

//***************************************************************************
//
//	This class holds visual information about player
//
//***************************************************************************
public class PlayerInfo extends ObjectInfo {
    String m_teamName = "";
    int m_uniformName = 0;        // recognise 0 as not being able to see number
    double m_bodyDir;
    double m_headDir;
    boolean m_goalie = false;

    //===========================================================================
    // Initialization member functions
    public PlayerInfo() {
        super("player");
    }

    public PlayerInfo(String team, int number, boolean is_goalie) {
        super("player");
        m_teamName = team;
        m_uniformName = number;
        m_goalie = is_goalie;
        m_bodyDir = 0;
        m_headDir = 0;
    }

    public PlayerInfo(String team, int number, double bodyDir, double headDir) {
        super("player");
        m_teamName = team;
        m_uniformName = number;
        m_bodyDir = bodyDir;
        m_headDir = headDir;
    }

    public String getTeamName() {
        return m_teamName;
    }

    public void setGoalie(boolean goalie) {
        m_goalie = goalie;
    }

    public boolean isGoalie() {
        return m_goalie;
    }

    public int getTeamNumber() {
        return m_uniformName;
    }

    public double getBodyDir() {
        return m_bodyDir;
    }

    public double getHeadDir() {
        return m_headDir;
    }
}