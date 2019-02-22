package inf112.skeleton.app.gameLogic.enums;

import inf112.skeleton.app.Game.Enum.ActionType;

public enum CardType {
    ROTATE_U("U-Turn", ActionType.ROTATE_U, 6),
    ROTATE_LEFT("Rotate Left", ActionType.ROTATE_L, 18),
    ROTATE_RIGHT("Rotate Right", ActionType.ROTATE_R, 18),
    BACK_UP("Back Up", ActionType.MOVE_BACK, 6),
    MOVE_1("Move 1", ActionType.MOVE_1, 18),
    MOVE_2("Move 2", ActionType.MOVE_2, 12),
    MOVE_3("Move 3", ActionType.MOVE_3, 6);


    private String description;
    private int numberOfCard;
    private ActionType actionType;

    CardType(String description, ActionType actionType, int numOfCard) {
        this.description = description;
        this.numberOfCard = numOfCard;
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Rotation getRotationDelta() {
        return rotation;
    }
}
