package inf112.skeleton.app.Enum;

public enum CardType {
    ROTATE_LEFT("Turn Left", 0, -90),
    ROTATE_RIGHT("Turn Right", 0, 90),
    ROTATE_U("U-Turn", 0, 180),
    MOVE_1("Move 1 Forward", 1, 0),
    MOVE_2("Move 2 Forward", 2, 0),
    MOVE_3("Move 3 Forward", 3, 0),
    BACK_UP("Move 1 Backwards", -1, 0);

    private String description;
    private int moveDelta;
    private int rotationDelta;

    CardType(String description, int moveDelta, int rotationDelta){
        this.description = description;
        this.moveDelta = moveDelta;
        this.rotationDelta = rotationDelta;
    }

    public String getDescription(){
        return description;
    }

    public int getMoveDelta() {
        return moveDelta;
    }

    public int getRotationDelta() {
        return rotationDelta;
    }
}
