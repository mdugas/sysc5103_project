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
//	This is base class for different classese with visual information
//	about objects
//
//***************************************************************************
public class ObjectInfo {
    public String m_type;
    public double m_distance;
    public double m_direction;
    public double m_distChange;
    public double m_dirChange;

    //===========================================================================
    // Initialization member functions
    public ObjectInfo(String type) {
        m_type = type;
    }

    public double getDistance() {
        return m_distance;
    }

    public double getDirection() {
        return m_direction;
    }

    public double getDistChange() {
        return m_distChange;
    }

    public double getDirChange() {
        return m_dirChange;
    }

    public String getType() {
        return m_type;
    }
}