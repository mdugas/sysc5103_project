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
//	This class holds visual information about flag
//
//***************************************************************************
public class FlagInfo extends ObjectInfo {
    char m_kind;  // p|g
    char m_pos1;  // t|b|l|c|r
    char m_pos2;  // l|r|t|c|b
    int m_num;    // 0|10|20|30|40|50
    boolean m_out;

    //===========================================================================
    // Initialization member functions
    public FlagInfo() {
        super("flag");
        m_kind = ' ';
        m_pos1 = ' ';
        m_pos2 = ' ';
        m_num = 0;
        m_out = false;
    }

    public FlagInfo(String flagType, char type, char pos1, char pos2,
                    int num, boolean out) {
        super(flagType);
        m_kind = type;
        m_pos1 = pos1;
        m_pos2 = pos2;
        m_num = num;
        m_out = out;
    }

    public FlagInfo(char type, char pos1, char pos2, int num, boolean out) {
        super("flag");
        m_kind = type;
        m_pos1 = pos1;
        m_pos2 = pos2;
        m_num = num;
        m_out = out;
    }

    public char getKind() {
        return m_kind;
    }

    public char getPos1() {
        return m_pos1;
    }

    public char getPos2() {
        return m_pos2;
    }

    public int getNum() {
        return m_num;
    }

    public boolean isOut() {
        return m_out;
    }
}