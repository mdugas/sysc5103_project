package ca.carleton.sce;

//***************************************************************************
//
//	This is base class for different classes with visual information
//	about objects
//
//***************************************************************************
class ObjectInfo {
  public String m_type;
  public float m_distance;
  public float m_direction;
  public float m_distChange;
  public float m_dirChange;

  //===========================================================================
  // Initialization member functions
  public ObjectInfo(String type) {
    m_type = type;
  }

  public float getDistance() {
    return m_distance;
  }

  public float getDirection() {
    return m_direction;
  }

  public float getDistChange() {
    return m_distChange;
  }

  public float getDirChange() {
    return m_dirChange;
  }

  public String getType() {
    return m_type;
  }
}
