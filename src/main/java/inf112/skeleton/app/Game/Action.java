package inf112.skeleton.app.Game;

import inf112.skeleton.app.Game.Enum.ActionType;
import inf112.skeleton.app.Game.Enum.Rotation;

public class Action {
    private Player player;
    private ActionType actionType;
    private int amount;
    private Rotation rotation;

    public Action (Player player, ActionType actionType, Rotation rotation, int amount){
        this.player = player;
        this.actionType = actionType;
        this.amount = amount;
        this.rotation = rotation;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Player getPlayer() {
        return player;
    }

    public int getAmount() {
        return amount;
    }
}
