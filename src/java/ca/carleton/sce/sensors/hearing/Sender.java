package ca.carleton.sce.sensors.hearing;

public enum Sender {
    Self,
    Referee,
    LeftCoach,
    RightCoach,
    Player;

    private int direction;

    public static Sender parse(String dir) {
        switch (dir) {
            case "self":
            case "Self":
                return Self;
            case "referee":
            case "Referee":
                return Referee;
            case "online_coach_left":
            case "LeftCoach":
                return LeftCoach;
            case "online_coach_right":
            case "RightCoach":
                return RightCoach;
            default:
                Sender sender = Player;
                sender.direction = Integer.parseInt(dir);
                return sender;
        }
    }

    public int getDirection() {
        return this.direction;
    }
}