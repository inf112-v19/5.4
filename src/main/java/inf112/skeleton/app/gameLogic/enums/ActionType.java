package inf112.skeleton.app.gameLogic.enums;

import javax.swing.*;

public enum ActionType {
    DAMAGE_1("1 DMG", ActionTypeType.DAMAGE, 1),
    DAMAGE_2("2 DMG", ActionTypeType.DAMAGE, 2),
    DAMAGE_3("3 DMG", ActionTypeType.DAMAGE, 3),
    MOVE_1("MOVE 1", ActionTypeType.MOVE, 1),
    MOVE_2("MOVE 2", ActionTypeType.MOVE, 2),
    MOVE_3("MOVE 3", ActionTypeType.MOVE, 3),
    MOVE_BACK("MOVE BACK", ActionTypeType.MOVE, -1),
    ROTATE_L("TURN LEFT", ActionTypeType.ROTATE, Rotation.L),
    ROTATE_R("TURN RIGHT", ActionTypeType.ROTATE, Rotation.R),
    ROTATE_U("U TURN", ActionTypeType.ROTATE, Rotation.U),
    PUSH_NORTH("PUSH NORTH", ActionTypeType.PUSH, Direction.NORTH),
    PUSH_SOUTH("PUSH SOUTH", ActionTypeType.PUSH, Direction.SOUTH),
    PUSH_EAST("PUSH EAST", ActionTypeType.PUSH, Direction.EAST),
    PUSH_WEST("PUSH WEST", ActionTypeType.PUSH, Direction.WEST);

    private String description;
    private ActionTypeType actionTypeType;
    private int value;
    private Direction direction;
    private Rotation rotation;

    ActionType(String description, ActionTypeType actionTypeType, int value) {
        this.description = description;
        this.actionTypeType = actionTypeType;
        this.value = value;
        this.direction = null;
        this.rotation = null;
    }

    ActionType(String description, ActionTypeType actionTypeType, Rotation rotation) {
            this.description = description;
        this.actionTypeType = actionTypeType;
            this.value = 0;
            this.direction = null;
            this.rotation = rotation;

    }

    ActionType(String description, ActionTypeType actionTypeType, Direction direction) {
        this.description = description;
        this.actionTypeType = actionTypeType;
        this.value = 0;
        this.direction = direction;
        this.rotation = null;
    }



    public String getDescription() {
        return description;
    }

    public ActionTypeType getActionTypeType() {
        return actionTypeType;
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
