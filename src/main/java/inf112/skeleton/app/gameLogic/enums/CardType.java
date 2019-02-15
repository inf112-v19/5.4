package inf112.skeleton.app.gameLogic.enums;

public enum CardType {
    ROTATE_U("U-Turn", 6, 0, Rotation.U),
    ROTATE_LEFT("Rotate Left", 18, 0, Rotation.L),
    ROTATE_RIGHT("Rotate Right", 18, 0, Rotation.R),
    BACK_UP("Move 1", 6, -1, null),
    MOVE_1("Move 1", 18, 1, null),
    MOVE_2("Move 2", 12, 2, null),
    MOVE_3("Move 3", 6, 3, null);


    private String description;
    private int numberOfCard;
    private int moveDistance;
    private Rotation rotation;

    CardType(String description, int numOfCard, int moveDistance, Rotation rotation){
        this.description = description;
        this.moveDistance = moveDistance;
        this.rotation = rotation;
        this.numberOfCard = numOfCard;

    }

    public int getNumberOfCard() {
        return numberOfCard;
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
