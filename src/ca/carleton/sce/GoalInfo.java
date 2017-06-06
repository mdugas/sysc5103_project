package ca.carleton.sce;

//***************************************************************************
//
//	This class holds visual information about goal
//
//***************************************************************************
class GoalInfo extends ObjectInfo {

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


