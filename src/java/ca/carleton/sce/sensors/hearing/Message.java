package ca.carleton.sce.sensors.hearing;

public class Message {
    private int time;
    private Sender sender;
    private String message;

    public Message(int time, Sender sender, String message) {
        this.time = time;
        this.sender = sender;
        this.message = message;
    }

    public int getTime() {
        return time;
    }

    public Sender getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
