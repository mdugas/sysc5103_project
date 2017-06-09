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
//	This class holds visual information about goal
//
//***************************************************************************
public class GoalInfo extends ObjectInfo {
    private char m_side;

    //===========================================================================
    // Initialization member functions
    public GoalInfo() {
        super("goal");
        m_side = ' ';
    }

    public GoalInfo(char side) {
        super("goal " + side);
        m_side = side;
    }

    public char getSide() {
        return m_side;
    }
}