package inf112.skeleton.app.Enum;

public enum CardType {
    ROTATE_LEFT("Turn Left", 0, Rotation.L),
    ROTATE_RIGHT("Turn Right", 0, Rotation.R),
    ROTATE_U("U-Turn", 0, Rotation.U),
    MOVE_1("Move 1 Forward", 1, null),
    MOVE_2("Move 2 Forward", 2, null),
    MOVE_3("Move 3 Forward", 3, null),
    BACK_UP("Move 1 Backwards", -1, null);

    private String description;
    private int moveDistance;
    private Rotation rotation;

    CardType(String description, int moveDistance, Rotation rotation){
        this.description = description;
        this.moveDistance = moveDistance;
        this.rotation = rotation;
    }

    public String getDescription(){
        return description;
    }

    public int getMoveDistance() {
        return moveDistance;
    }

    public Rotation getRotationDelta() {
        return rotation;
    }
}
