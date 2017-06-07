package ca.carleton.sce;

//***************************************************************************
//
//	This class holds visual information about line
//
//***************************************************************************
class LineInfo extends ObjectInfo
{

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
}
