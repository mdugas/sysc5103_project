package ca.carleton.sce;

//***************************************************************************
//
//	This interface declares functions which are used to send
//	command to player
//
//***************************************************************************
interface SendCommand
{
    // This function sends move command to the server
    void move(double x, double y);
    // This function sends turn command to the server
    void turn(double moment);
    void turn_neck(double moment);
    // This function sends dash command to the server
    void dash(double power);
    // This function sends kick command to the server
    void kick(double power, double direction);
    // This function sends say command to the server
    void say(String message);
    // This function sends change_view command to the server
    void changeView(String angle, String quality);
    // This function sends a bye command to the server
    void bye();
}

