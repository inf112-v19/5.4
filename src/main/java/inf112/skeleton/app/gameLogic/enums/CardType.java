package inf112.skeleton.app.gameLogic.enums;

import inf112.skeleton.app.Game.Enum.ActionType;

public enum CardType {
    ROTATE_U("U-Turn", ActionType.TURN, Rotation.U, 0, 6),
    ROTATE_LEFT("Rotate Left", ActionType.TURN, Rotation.L, 0, 18),
    ROTATE_RIGHT("Rotate Right", ActionType.TURN, Rotation.R, 0, 18),
    BACK_UP("Back Up", ActionType.MOVE, null, -1, 6),
    MOVE_1("Move 1", ActionType.MOVE, null, 1, 18),
    MOVE_2("Move 2", ActionType.MOVE, null, 2, 12),
    MOVE_3("Move 3", ActionType.MOVE, null, 3, 6);


    private String description;
    private int numberOfCard;
    private int moveDistance;
    private Rotation rotation;
    private ActionType actionType;

    CardType(String description, ActionType actionType, Rotation rotation, int moveDistance, int numOfCard) {
        this.description = description;
        this.moveDistance = moveDistance;
        this.rotation = rotation;
        this.numberOfCard = numOfCard;
        this.actionType = actionType;

    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }

    public String getDescription() {
        return description;
    }

    public int getMoveDistance() {
        return moveDistance;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Rotation getRotationDelta() {
        return rotation;
    }
}
