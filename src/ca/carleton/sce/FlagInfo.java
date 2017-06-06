package ca.carleton.sce;

//***************************************************************************
//
//	This class holds visual information about flag
//
//***************************************************************************
class FlagInfo extends ObjectInfo {

    char m_type;  // p|g
    char m_pos1;  // t|b|l|c|r
    char m_pos2;  // l|r|t|c|b
    int m_num;    // 0|10|20|30|40|50
    boolean m_out;

    //===========================================================================
    // Initialization member functions
    public FlagInfo() {
        super("flag");
        m_type = ' ';
        m_pos1 = ' ';
        m_pos2 = ' ';
        m_num = 0;
        m_out = false;
    }

    public FlagInfo(String flagType, char type, char pos1, char pos2, int num, boolean out) {
        super(flagType);
        m_type = type;
        m_pos1 = pos1;
        m_pos2 = pos2;
        m_num = num;
        m_out = out;
    }

    public FlagInfo(char type, char pos1, char pos2, int num, boolean out) {
        super("flag");
        m_type = type;
        m_pos1 = pos1;
        m_pos2 = pos2;
        m_num = num;
        m_out = out;
    }
}

