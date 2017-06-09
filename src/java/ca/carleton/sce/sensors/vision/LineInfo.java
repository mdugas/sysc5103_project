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
//	This class holds visual information about line
//
//***************************************************************************
public class LineInfo extends ObjectInfo {
    char m_kind;  // l|r|t|b

    //===========================================================================
    // Initialization member functions
    public LineInfo() {
        super("line");
    }

    public LineInfo(char kind) {
        super("line");
        m_kind = kind;
    }

    public char getKind() {
        return m_kind;
    }
}