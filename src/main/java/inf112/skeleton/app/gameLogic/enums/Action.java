package inf112.skeleton.app.gameLogic.enums;

public enum Action {
    DAMAGE_1("1 DMG", ActionType.DAMAGE, 1),
    DAMAGE_2("2 DMG", ActionType.DAMAGE, 2),
    DAMAGE_3("3 DMG", ActionType.DAMAGE, 3),
    MOVE_1("MOVE 1", ActionType.MOVE, 1),
    MOVE_2("MOVE 2", ActionType.MOVE, 2),
    MOVE_3("MOVE 3", ActionType.MOVE, 3),
    MOVE_BACK("MOVE BACK", ActionType.MOVE, -1),
    ROTATE_L("TURN LEFT", ActionType.ROTATE, Rotation.L),
    ROTATE_R("TURN RIGHT", ActionType.ROTATE, Rotation.R),
    ROTATE_U("U TURN", ActionType.ROTATE, Rotation.U);

    private String description;
    private ActionType actionType;
    private int value;
    private Direction direction;
    private Rotation rotation;

    Action(String description, ActionType actionType, int value) {
        this.description = description;
        this.actionType = actionType;
        this.value = value;
        this.direction = null;
        this.rotation = null;
    }

    Action(String description, ActionType actionType, Rotation rotation) {
            this.description = description;
        this.actionType = actionType;
            this.value = 1;
            this.direction = null;
            this.rotation = rotation;

    }

    Action(String description, ActionType actionType, Direction direction) {
        this.description = description;
        this.actionType = actionType;
        this.value = 0;
        this.direction = direction;
        this.rotation = null;
    }



    public String getDescription() {
        return description;
    }

    public ActionType getActionType() {
        return actionType;
    }


    public int getValue() {
        return value;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rotation getRotation() {
        return rotation;
    }
}
