package ca.carleton.sce;

//***************************************************************************
//
//	This class holds visual information about player
//
//***************************************************************************
class PlayerInfo extends ObjectInfo {

    String  m_teamName = "";
    int m_uniformName = 0;        // recognise 0 as not being able to see number
    float m_bodyDir;
    float m_headDir;
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

    public PlayerInfo(String team, int number, float bodyDir, float headDir) {
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
}


