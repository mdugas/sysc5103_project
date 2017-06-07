package ca.carleton.sce;

class Memory {

    //===========================================================================
    // Private members
    volatile private VisualInfo	m_info;	// place where all information is stored
    final static int SIMULATOR_STEP = 100;

    //---------------------------------------------------------------------------
    // This constructor:
    // - initializes all variables
    public Memory() {
    }


    //---------------------------------------------------------------------------
    // This function puts see information into our memory
    public void store(VisualInfo info) {
        m_info = info;
    }

    //---------------------------------------------------------------------------
    // This function looks for specified object
    public ObjectInfo getObject(String name) {
        if( m_info == null ) {
            waitForNewInfo();
        }

        for(int c = 0 ; c < m_info.m_objects.size() ; c ++) {
            ObjectInfo object = m_info.m_objects.elementAt(c);
            if(object.m_type.compareTo(name) == 0)
                return object;
        }

        return null;
    }


    //---------------------------------------------------------------------------
    // This function waits for new visual information
    public void waitForNewInfo() {
        // first remove old info
        m_info = null;
        // now wait until we get new copy
        while(m_info == null) {
            // We can get information faster then 75 milliseconds
            try {
                Thread.sleep(SIMULATOR_STEP);
            }
            catch(Exception e) {
                // Do nothing
            }
        }
    }
}

