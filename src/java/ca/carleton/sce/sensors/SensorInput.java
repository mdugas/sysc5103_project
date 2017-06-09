package ca.carleton.sce.sensors;

import ca.carleton.sce.sensors.hearing.Message;
import ca.carleton.sce.sensors.vision.VisualInfo;

public interface SensorInput
{
    //---------------------------------------------------------------------------
    // This function sends see information
    void see(VisualInfo info);

    //---------------------------------------------------------------------------
    // This function receives hear information from player
    void hear(Message message);
}
