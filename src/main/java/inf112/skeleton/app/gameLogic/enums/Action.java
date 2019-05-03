package inf112.skeleton.app.gameLogic.enums;

public enum Action {
    DAMAGE_1("1 DMG", ActionType.DAMAGE, 1),
    DAMAGE_2("2 DMG", ActionType.DAMAGE, 2),
    DAMAGE_3("3 DMG", ActionType.DAMAGE, 3),
    MOVE_0("MOVE 0", ActionType.MOVE, 0),
    MOVE_1("MOVE 1", ActionType.MOVE, 1),
    MOVE_2("MOVE 2", ActionType.MOVE, 2),
    MOVE_3("MOVE 3", ActionType.MOVE, 3),
    MOVE_BACK("MOVE BACK", ActionType.MOVE, 1),
    ROTATE_L("TURN LEFT", ActionType.ROTATE, Rotation.L),
    ROTATE_R("TURN RIGHT", ActionType.ROTATE, Rotation.R),
    ROTATE_U("U TURN", ActionType.ROTATE, Rotation.U),
    DIE("DIE", ActionType.DIE, 0);

    private String description;
    private ActionType actionType;
    private int value;
    private Rotation rotation;

    Action(String description, ActionType actionType, int value) {
        this.description = description;
        this.actionType = actionType;
        this.value = value;
        this.rotation = null;
    }

    Action(String description, ActionType actionType, Rotation rotation) {
        this.description = description;
        this.actionType = actionType;
        this.value = 1;
        this.rotation = rotation;
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

    public Rotation getRotation() {
        return rotation;
    }
}
